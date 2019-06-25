package spark.sparksql

import java.util.Properties

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Row, SQLContext, SparkSession}
/**
  * Created by 邵洋 on 2017/3/2.
  */
case class Person(name: String, age: Long)

object SparkSQLDemo {

  def main(args: Array[String]) {
    val appname = this.getClass.getSimpleName
    val spark=SparkSession.builder().appName(appname).master("local[2]").getOrCreate()
    //使支持RDDs转换为DataFrames及后续sql操作
    import spark.implicits._
    val df = spark.read.json("people.json")
    df.show()
    df.printSchema()

    df.select(df("name"),df("age")).show()
    df.filter(df("age")>20).show()

    //df.select("name", "age").write.csv("E:\\IdeaProjects15\\SparkExample\\people.csv")
    spark.read.format("csv").load("E:\\IdeaProjects15\\SparkExample\\people.csv").show()

    //使用编程方式定义RDD模式
    val fields = Array(StructField("name",StringType,true), StructField("age",IntegerType,true))
    val schema = StructType(fields)
    val peopleRDD = spark.sparkContext.textFile("people.txt")
    val rowRDD = peopleRDD.map(_.split(",")).map(attributes => Row(attributes(0), attributes(1).trim.toInt))
    val peopleDF = spark.createDataFrame(rowRDD, schema)
    peopleDF.createOrReplaceTempView("people")
    val results = spark.sql("SELECT name,age FROM people")
    results.show()
    results.map(attributes => "name: " + attributes(0)+","+"age:"+attributes(1)).show()

    //下面我们设置两条数据表示两个学生信息
    val studentRDD = spark.sparkContext.parallelize(Array("5 星矢 男 26","6 雅典娜 女 27")).map(_.split(" "))
    //下面要设置模式信息
    val schemajdbc = StructType(List(StructField("id", IntegerType, true),StructField("name", StringType, true),StructField("gender", StringType, true),StructField("age", IntegerType, true)))
    //下面创建Row对象，每个Row对象都是rowRDD中的一行
    val rowRDDjdbc = studentRDD.map(p => Row(p(0).toInt, p(1).trim, p(2).trim, p(3).toInt))
    //建立起Row对象和模式之间的对应关系，也就是把数据和模式对应起来
    val studentDF = spark.createDataFrame(rowRDDjdbc, schemajdbc)
    //下面创建一个prop变量用来保存JDBC连接参数
    val prop = new Properties()
    prop.put("user", "root") //表示用户名是root
    prop.put("password", "123456") //表示密码是hadoop
    prop.put("driver","com.mysql.jdbc.Driver")
    //下面就可以连接数据库，采用append模式，表示追加记录到数据库spark的student表中
    studentDF.write.mode("append").jdbc("jdbc:mysql://127.0.0.1:3306/spark", "spark.student", prop)

    val jdbcDF = spark.read.format("jdbc").
      option("url","jdbc:mysql://127.0.0.1:3306/spark").
      option("driver","com.mysql.jdbc.Driver").
      option("dbtable", "student").
      option("user", "root").option("password", "123456").load()

    jdbcDF.show()


    spark.stop()
  }
}