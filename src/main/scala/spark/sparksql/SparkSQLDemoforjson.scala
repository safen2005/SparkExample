package spark.sparksql

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.{SQLContext}
/**
  * Created by 邵洋 on 2017/3/2.
  */
case class Personjson(name: String, age: Int)

object SparkSQLDemoforjson {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkSQLDemo").setMaster("local")
    conf.set("spark.testing.memory", "2147480000")//后面的值大于512m即可
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val people = sqlContext.read.json("E:\\IdeaProjects\\files\\people.json")
    people.show()

    people.registerTempTable("Personjson")
    sqlContext.sql("select * from  Personjson").show
    val teenagers = sqlContext.sql("SELECT name,age FROM Personjson WHERE age >= 11 AND age <= 40")
    teenagers.map(t => "Name: " + t(0)).collect().foreach(println)
    teenagers.map(t => "age: " + t(1)).collect().foreach(println)
    teenagers.rdd.map(t=>(t(0),t(1))).map(t=>(t._1,t._2)).foreach(println)

//    val people2 = sqlContext.read.format("json").load("E:\\IdeaProjects\\files\\people.json")
//    people2.show()
  }
}