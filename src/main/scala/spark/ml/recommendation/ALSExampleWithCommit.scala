package spark.ml.recommendation

// $example on$
import com.alibaba.fastjson.{JSONArray, JSONObject}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.recommendation.ALS
import org.apache.spark.sql.{Row, SaveMode}
import org.apache.spark.sql.types.StructType

import scala.collection.mutable
import scala.reflect.ClassTag
// $example off$
import org.apache.spark.sql.SparkSession

object ALSExampleWithCommit {

  // $example on$
  /**  当你声明了一个 case class（样例类）时，scala会帮助我们做下面几件事情：
  1 构造器中的参数如果不被声明为var的话，它默认的话是val类型的，但一般不推荐将构造器中的参数声明为var
  2 自动创建伴生对象，同时在里面给我们实现子apply方法，使得我们在使用的时候可以不直接显示地new对象
  3 伴生对象中同样会帮我们实现unapply方法，从而可以将case class应用于模式匹配
  4 实现自己的toString、hashCode、copy、equals方法
    */
  case class Rating(userId: Int, movieId: Int, rating: Float, timestamp: Long)
  // 数据示例：0::2::3::1424380312
  def parseRating(str: String): Rating = {
    val fields = str.split("::")
    //    assert() 或 assume() 方法在对中间结果或私有方法的参数进行检验，不成功则抛出 AssertionError 异常
    assert(fields.size == 4)
    //      返回分割好的Rating对象,注意用户Id列以及产品id列，必须是整数（如果是其他数值类型，只要在整形范围内，都会转为integers）
    Rating(fields(0).toInt, fields(1).toInt, fields(2).toFloat, fields(3).toLong)
  }

  // 标准推荐对象，productId,score
  case class Recommendation(productId: Int, score:Double)

  // 用户推荐列表
  case class UserRecs(userId: Int, recs: Seq[Recommendation])

  // 用户推荐列表2
  case class UserRecsString(userId: Int, recs: String)

  // 商品相似度（商品推荐）
  case class ProductRecs(productId: Int, recs: Seq[Recommendation])

  // $example off$

  def main(args: Array[String]) {
    Logger.getLogger("org.apache.hadoop").setLevel(Level.ERROR)
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val spark = SparkSession
      .builder
      .master("local[2]")
      .appName("ALSExampleWithCommit")
      .getOrCreate()
    import spark.implicits._

    // $example on$
    val ratings = spark.read.textFile("data/mllib/als/sample_movielens_ratings.txt")
      .map(parseRating)
      .toDF()
    ratings.printSchema()
    ratings.show(5)
    //    切分训练集和测试集
    val Array(training, test) = ratings.randomSplit(Array(0.8, 0.2))

    // Build the recommendation model using ALS on the training data
    val als = new ALS()
      .setRank(8) //  对应ALS模型中的因子个数，即矩阵分解出的两个矩阵的新的行/列数，即A≈UVT,k<<m,n中的k。
      .setMaxIter(20) // 默认10
      .setRegParam(0.01) // 正则化参数
      .setImplicitPrefs(true) // 使用隐式反馈
      .setUserCol("userId")
      .setItemCol("movieId")
      .setRatingCol("rating")
    //使用ALS在训练数据上建立推荐模型
    val model = als.fit(training)
    model.userFactors.show(5)
    //model.userFactors.select("features").rdd.foreach(println)
    model.itemFactors.show(5)

    // transform操作会增加一个prediction列
    val predictions = model.transform(test)
    predictions.printSchema()
    predictions.show(5)
    // 通过计算测试集上的均方根误差来评估模型
    val evaluator = new RegressionEvaluator()
      .setMetricName("rmse")
      .setLabelCol("rating")
      .setPredictionCol("prediction")
    val rmse = evaluator.evaluate(predictions)
    println(s"Root-mean-square error = $rmse")

    // Generate top 10 movie recommendations for each user
    val userRecs = model.recommendForAllUsers(3)
    userRecs.show(5)
    userRecs.select("userId", "recommendations").rdd.map(r => (r.getInt(0), parseRow2String(r))).toDF("userId","recommendations")
      .write.mode(SaveMode.Overwrite)
      .format("jdbc")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("url", "jdbc:mysql://172.16.59.13:10065/daas_test?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true")
      .option("dbtable", "recommendForAllUsers")
      .option("user", "daas_test_admin")
      .option("password", "cGdyr7ce0D9wYMkg")
      .save()

    userRecs.select("recommendations").rdd.map((r: Row) =>{
      val row: Seq[Int] = r.getAs[Seq[Row]](0).map(x =>{
        x.getInt(0)
      })
      row.mkString(",")
    }).toDF().show()

    //userRecs.select("userId", "recommendations").map(r => (r.getInt(0), parseList2String(r.getSeq(1).mkString("&").replace("[","").replace("]",""))))
//    userRecs.select("userId", "recommendations").map(r => (r.getInt(0), parseSeq2String(r.getSeq(1))))
//      .write.mode(SaveMode.Overwrite)
//      .format("jdbc")
//      .option("driver", "com.mysql.jdbc.Driver")
//      .option("url", "jdbc:mysql://172.16.59.13:10065/daas_test?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true")
//      .option("dbtable", "recommendForAllUsers")
//      .option("user", "daas_test_admin")
//      .option("password", "cGdyr7ce0D9wYMkg")
//      .save()

    // Generate top 10 user recommendations for each movie
    val movieRecs = model.recommendForAllItems(3)
    movieRecs.show(5)
    // Generate top 10 movie recommendations for a specified set of users
    val users = ratings.select(als.getUserCol).distinct().limit(3)
    users.show()
    val userSubsetRecs = model.recommendForUserSubset(users, 10)
    userSubsetRecs.show()
    // Generate top 10 user recommendations for a specified set of movies
    val movies = ratings.select(als.getItemCol).distinct().limit(3)
    movies.show()
    val movieSubSetRecs = model.recommendForItemSubset(movies, 10)
    movieSubSetRecs.show()
    // $example off$
    spark.stop()
  }

  def parseRow2String(r: Row): String={
    val row: Seq[Int] = r.getAs[Seq[Row]](1).map(x =>{
      x.getInt(0)
    })
    row.mkString(",")
  }

  def parseSeq2String(par :scala.collection.Seq[StructType]): String={
    val empty: java.util.List[String] = new java.util.ArrayList[String](par.size)
    for (i <- 0 until par.size) {
      empty.add(par.apply(i)+"")
    }
    empty.toArray.mkString(",")
  }

  def parseList2String(par :String): String={
    val pararr= par.split("&")
    val empty: java.util.List[String] = new java.util.ArrayList[String](pararr.size)
    for (i <- 0 until pararr.size) {
      empty.add(pararr(i).split(",")(0))
    }
    empty.toArray.mkString(",")
  }
}