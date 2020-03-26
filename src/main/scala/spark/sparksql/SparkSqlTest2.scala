package spark.sparksql

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkSqlTest2 {

  def main(args: Array[String]): Unit = {
    val appName = this.getClass.getSimpleName
    println("appName = "+appName)

    // 定义配置
    val config = Map(
      "spark.cores" -> "local[2]",
      "driver" -> "com.mysql.jdbc.Driver",
      "user" -> "root",
      "password"-> "123456"
    )

    val spark = SparkSession.
                builder().
                config("spark.sql.crossJoin.enabled",true).
                appName(appName).
                master(config("spark.cores")).
                getOrCreate()

        val prop: Properties = new Properties()
        prop.put("driver",config("driver"))
        prop.put("user", config("user"))
        prop.put("password", config("password"))

    // 取得该表数据
    val p1DF = spark.read.format("jdbc")
      .option("driver", config("driver"))
      .option("url", "jdbc:mysql://127.0.0.1:3306/spark?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true")
      .option("dbtable", "p1")
      .option("user", config("user"))
      .option("password", config("password"))
      .load()
    p1DF.show()

    val p2DF = spark.read.jdbc("jdbc:mysql://127.0.0.1:3306/?useUnicode=true&characterEncoding=UTF-8","spark.p2",prop)
    p2DF.show()

    val usecols = Seq("id","uid")
    p1DF.join(p2DF,usecols).show()

    p1DF.registerTempTable("p1")
    spark.sql("select id,uid,age,'2020-3-26' as dd from p1").show()
    spark.sql("select id,uid,age,'2020-3-26' as dd from p1").select("id","uid","age","dd").join(p2DF.select("id","uid","name"),usecols,"left").write.mode(SaveMode.Overwrite)
      .format("jdbc")
      .option("driver", config("driver"))
      .option("url", "jdbc:mysql://127.0.0.1:3306/spark?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true")
      .option("dbtable", "p3")
      .option("user", config("user"))
      .option("password", config("password"))
      .save()

    //p1DF.join(p2DF,usecol,"right").show()
    //p1DF.join(p2DF,usecol,"full").show()
    spark.stop()
  }
}