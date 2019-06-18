package spark.sparksql

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
/**
  * Created by 邵洋 on 2017/3/2.
  */
case class Person(name: String, age: Int)

object SparkSQLDemo {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkSQLDemo").setMaster("local")
    conf.set("spark.testing.memory", "2147480000")//后面的值大于512m即可
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val people = sc.textFile("E:\\IdeaProjects\\files\\people.txt")
      .map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt)).toDF()
    people.registerTempTable("people")
    people.show()
    //sqlContext.sql("select * from  people").show

    val teenagers = sqlContext.sql("SELECT name FROM people WHERE age >= 13 AND age <= 19")
    //teenagers.map(t => "Name: " + ByteUtils.toUTF8String(t(0).toString.getBytes)).collect().foreach(println)
    println("--------cache start---------------------")
    teenagers.cache()
    teenagers.collect()
    println("--------collect end---------------------")
    println(people.collectAsList().size())
    teenagers.map(t => "Name: " + t(0)).collect().foreach(println)

    people.printSchema()

    people.collect().foreach(println)
    println(people.collectAsList().size())
    println(people.count())
  }
}