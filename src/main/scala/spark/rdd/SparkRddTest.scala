package spark.rdd

import com.alibaba.fastjson.JSON
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}
import org.slf4j.{Logger, LoggerFactory}

object SparkRddTest {
  case class ProductRating(userId: Int, productId: Int, score: Double, timestamp: Int)
  // 标准推荐对象，productId,score
  case class Recommendation(productId: Int, score:Double)

  def parseRating(str: String): ProductRating = {
    val fields = str.split("::")
    assert(fields.size == 4)
    ProductRating(fields(0).toInt, fields(1).toInt, fields(2).toFloat, fields(3).toInt)
  }

  def main(args: Array[String]): Unit = {
    val LOG: Logger = LoggerFactory.getLogger(this.getClass)
    val appname = this.getClass.getSimpleName
    LOG.info(appname)

    val spark = SparkSession.builder().master("local[2]").appName(appname).getOrCreate()
    val sc = spark.sparkContext

    import spark.implicits._
    // 读取数据
    val ratingDF = spark.read.textFile("data/mllib/als/sample_movielens_ratings.txt")
      .map(parseRating).toDF()
    ratingDF.show(5)

    //设置schema结构
    val userIdSchema = StructType(
      Seq(
        StructField("userId", IntegerType, true),
        StructField("score", DoubleType, true)
      )
    )
    val productIdSchema = StructType(
      Seq(
        StructField("productId", IntegerType, true),
        StructField("score", DoubleType, true)
      )
    )

    val userRdd = sc.textFile("data/mllib/als/sample_movielens_ratings.txt").map(str=>{
      val fields = str.split("::")
      assert(fields.size == 4)
      Row(fields(0).toInt, fields(2).toDouble)
    })

    val userDF = spark.createDataFrame(userRdd, userIdSchema)
    //userDF.show()
    userDF.registerTempTable("user")
    spark.sql("select userId,count(score),max(score),sum(score) from user group by userId")

    val proRdd = sc.textFile("data/mllib/als/sample_movielens_ratings.txt").map(str=>{
      val fields = str.split("::")
      assert(fields.size == 4)
      Row(fields(1).toInt, fields(2).toDouble)
    })

    val proDF = spark.createDataFrame(proRdd, productIdSchema)
    //proDF.show()

    //spark.read.textFile("data/mllib/als/sample_movielens_ratings.txt").show(5)

    val sdklogSchema = StructType(
      Seq(
        StructField("e_id", StringType, true),
        StructField("is_inc", IntegerType, true),
        StructField("idx", IntegerType, true),
        StructField("idx_max", IntegerType, true),
        StructField("idx_max_y", IntegerType, true)
      )
    )
    val sdklogRdd = sc.textFile("E:\\\\IdeaProjects\\\\files\\\\psi_file_iDataIn_sdk_Da50c39927.txt").map(str=>{
      val js = JSON.parseObject(str)
      val jsonObject = js.getJSONObject("header")
      Row(jsonObject.getString("e_id"), jsonObject.getIntValue("is_inc"), jsonObject.getIntValue("idx"), jsonObject.getIntValue("idx_max"), jsonObject.getIntValue("idx_max_y"))
    })
    val sdklogDF = spark.createDataFrame(sdklogRdd, sdklogSchema)
    println(sdklogDF.collect().length)
//    val rdd = sc.textFile("word\\word.txt").filter(line=>{line.contains("spark")})
//    println(rdd.count())
//    println(rdd.collect().length+"")
//    println(rdd.first())
//    rdd.take(1).foreach(println)
//
//    val words=rdd.flatMap(line => line.split(" "))
//    println(words.count())
//    words.foreach(arr=>{
//      println(arr)
//    })
//    println(words.first())
//    words.take(2).foreach(println)
//
//    val wordskey = words.map(x=>{(x,1)})
//    wordskey.groupByKey().foreach(println)
//    wordskey.reduceByKey(_+_).saveAsTextFile("saveword.txt")
//
//    val array = Array(1,2,3,4,5)
//    val rddArray = sc.parallelize(array)
//    println(rddArray.collect().length+"")
//    println(rddArray.count())
//    println(rddArray.first())
//    rddArray.take(3).foreach(println)
//    println(rddArray.reduce((a,b)=>a+b))
//
//    val rddArray2=rddArray.map(x=>x+10)
//    rddArray2.foreach(println)
//
//    val list = List(1,2,3,4,5)
//    val rddList = sc.parallelize(list)
//    println(rddList.collect().length+"")
//
//    val lists = List("Hadoop","Spark","Hive")
//    val rdds = sc.parallelize(lists).persist()
//    rdds.map(x=>{x+"formap"})
//    println(rdds.count())  //行动操作，触发一次真正从头到尾的计算
//    println(rdds.collect().mkString(","))  //行动操作，触发一次真正从头到尾的计算

//    //创建RDD时手动指定分区个数
//    val array = Array(1,2,3,4,5)
//    val rdd = sc.parallelize(array,3)  //设置两个分区
//    println(rdd.partitions.size)
//    val rdd2 = rdd.repartition(2)//重分区
//    println(rdd2.partitions.size)

    //常用的键值对RDD转换操作 reduceByKey和groupByKey的区别
//    val words = List("one","two","two","three","three","three")
//    val wordPairsRDD = sc.parallelize(words).map(word => (word,1))
//    wordPairsRDD.reduceByKey(_+_).foreach(println)
//    wordPairsRDD.groupByKey().map(t=>(t._1,t._2.sum)).foreach(println)

//    val rdd = sc.parallelize(Array(("spark",2),("hadoop",6),("hadoop",4),("spark",6)))
//    rdd.mapValues(x => (x,1)).reduceByKey((x,y) => (x._1+y._1,x._2 + y._2)).mapValues(x => (x._1 / x._2)).collect().foreach(println)

    sc.stop()
    spark.stop()
  }

}