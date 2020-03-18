package spark.ml.recommendation

import com.alibaba.fastjson.{JSONArray, JSONObject}
import org.apache.spark.SparkConf
import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.sql.SparkSession
import org.jblas.DoubleMatrix

object OfflineRecommmeder {
  case class ProductRating(userId: Int, productId: Int, score: Double, timestamp: Int)

  // 标准推荐对象，productId,score
  case class Recommendation(productId: Int, score:Double)

  // 用户推荐列表
  case class UserRecs(userId: Int, recs: Seq[Recommendation])

  // 用户推荐列表2
  case class UserRecsString(userId: Int, recs: String)

  // 商品相似度（商品推荐）
  case class ProductRecs(productId: Int, recs: Seq[Recommendation])

  def parseRating(str: String): ProductRating = {
    val fields = str.split("::")
    assert(fields.size == 4)
    ProductRating(fields(0).toInt, fields(1).toInt, fields(2).toFloat, fields(3).toInt)
  }
  // 定义常量
  val MONGODB_RATING_COLLECTION = "Rating"

  // 推荐表的名称
  val USER_RECS = "UserRecs"
  val PRODUCT_RECS = "ProductRecs"

  val USER_MAX_RECOMMENDATION = 20

  def main(args: Array[String]): Unit = {
    // 定义配置
    val config = Map(
      "spark.cores" -> "local[*]",
      "mongo.uri" -> "mongodb://localhost:27017/recommender",
      "mongo.db" -> "recommender"
    )
    // 创建spark session
    val sparkConf = new SparkConf().setMaster(config("spark.cores")).setAppName("OfflineRecommender")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()

    import spark.implicits._
    // 读取数据
    val ratingRDD = spark.read.textFile("data/mllib/als/sample_movielens_ratings.txt")
      .map(parseRating).rdd.map(rating=> (rating.userId, rating.productId, rating.score)).cache()

    //用户的数据集 RDD[Int]
    val userRDD = ratingRDD.map(_._1).distinct()
    val prodcutRDD = ratingRDD.map(_._2).distinct()

    //创建训练数据集
    val trainData = ratingRDD.map(x => Rating(x._1,x._2,x._3))
    // rank 是模型中隐语义因子的个数, iterations 是迭代的次数, lambda 是ALS的正则化参
    val (rank,iterations,lambda) = (50, 5, 0.01)
    // 调用ALS算法训练隐语义模型
    val model = ALS.train(trainData,rank,iterations,lambda)
    //model.productFeatures.foreach(println)
    //model.userFeatures.foreach(println)
    //计算用户推荐矩阵
    val userProducts = userRDD.cartesian(prodcutRDD)
    userProducts.foreach(println)
    // model已训练好，把id传进去就可以得到预测评分列表RDD[Rating] (userId,productId,rating)
    val preRatings = model.predict(userProducts)
    preRatings.foreach(println)

//    val userRecs = preRatings
//      .filter(_.rating > 0)
//      .map(rating => (rating.user,(rating.product, rating.rating)))
//      .groupByKey()
//      .map{
//        case (userId,recs) => UserRecs(userId,recs.toList.sortWith(_._2 >_._2).take(USER_MAX_RECOMMENDATION).map(x => Recommendation(x._1,x._2)))
//      }.toDF()
//    userRecs.show(5)

    val userRecsString = preRatings
      .filter(_.rating > 0)
      .map(rating => (rating.user,(rating.product, rating.rating)))
      .groupByKey()
      .map{
        case (userId,recs) => UserRecsString(userId,parseList2JsonString(recs.toList.sortWith(_._2 >_._2).take(USER_MAX_RECOMMENDATION).map(x =>{
          val jsonobj = new JSONObject()
          jsonobj.put(x._1+"",x._2)
          jsonobj
        })))
      }
    userRecsString.foreach(println)
    //TODO：计算商品相似度矩阵
    //获取商品的特征矩阵，数据格式 RDD[(scala.Int, scala.Array[scala.Double])]
    val productFeatures = model.productFeatures.map{case (productId,features) =>
      (productId, new DoubleMatrix(features))
    }

    // 计算笛卡尔积并过滤合并
    val productRecs = productFeatures.cartesian(productFeatures)
      .filter{case (a,b) => a._1 != b._1}
      .map{case (a,b) =>
        val simScore = this.consinSim(a._2,b._2) // 求余弦相似度
        (a._1,(b._1,simScore))
      }.filter(_._2._2 > 0.6)
      .groupByKey()
      .map{case (productId,items) =>
        ProductRecs(productId,items.toList.map(x => Recommendation(x._1,x._2)))
      }.toDF()
    productRecs.show()
    // 关闭spark
    spark.stop()
  }

  def parseList2JsonString(list :scala.collection.immutable.List[JSONObject]): String={
    val jsonarr = new JSONArray()
    for (i <- 0 until list.size) {
     jsonarr.add(list(i))
    }
    jsonarr.toJSONString
  }

  //计算两个商品之间的余弦相似度
  def consinSim(product1: DoubleMatrix, product2:DoubleMatrix) : Double ={
    product1.dot(product2) / ( product1.norm2()  * product2.norm2() )
  }
}