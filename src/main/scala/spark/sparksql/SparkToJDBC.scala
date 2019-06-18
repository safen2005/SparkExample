package spark.sparksql

import java.sql.DriverManager

import org.apache.spark.SparkContext
import org.apache.spark.rdd.JdbcRDD

/**
  * Created by 邵洋 on 2017/3/3.
  */
object SparkToJDBC {

  /*JdbcRDD[T: ClassTag](
    sc: SparkContext,
    getConnection: () => Connection,
    sql: String,
    lowerBound: Long,
    upperBound: Long,
    numPartitions: Int,
    mapRow: (ResultSet) => T = JdbcRDD.resultSetToObjectArray _)*/

  def main(args: Array[String]) {
    val sc = new SparkContext("local", "mysql")
    val rdd = new JdbcRDD(
      sc,
      () => {
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jeecmsv8f", "root", "123456")
      },
      "SELECT title FROM jc_log WHERE log_id >= ? AND log_id <= ?",
      1, 100, 3,
      r => r.getString(1)).cache()

    println("含有“修改”的记录数 = "+rdd.filter(_.contains("修改")).count())
    rdd.filter(_.contains("修改")).collect().foreach(println)
    sc.stop()
  }
}
