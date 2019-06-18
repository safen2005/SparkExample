package spark.rdd

import org.apache.spark.sql.SparkSession
import org.slf4j.{Logger, LoggerFactory}

object SparkRddTest {

  def main(args: Array[String]): Unit = {
    val LOG: Logger = LoggerFactory.getLogger(this.getClass)
    val appname = this.getClass.getSimpleName
    LOG.info(appname)

    val spark = SparkSession.builder().master("local").appName(appname).getOrCreate()
    val sc = spark.sparkContext

    val rdd = sc.textFile("word\\word.txt").filter(line=>{line.contains("spark")})
    println(rdd.count())
    println(rdd.collect().length+"")
    println(rdd.first())
    rdd.take(1).foreach(println)
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
    val words = List("one","two","two","three","three","three")
    val wordPairsRDD = sc.parallelize(words).map(word => (word,1))
    wordPairsRDD.reduceByKey(_+_).foreach(println)
    wordPairsRDD.groupByKey().map(t=>(t._1,t._2.sum)).foreach(println)

    sc.stop()
    spark.stop()
  }

}