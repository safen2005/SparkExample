package com.mobin.sparkStreaming

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by 邵洋 on 6/23/19.
  */
object StateFull {
  def main(args: Array[String]) {
    val updateFunc = (values: Seq[Int], state: Option[Int]) => {
      val currentCount = values.foldLeft(0)(_ + _)
      val previousCount = state.getOrElse(0)
      Some(currentCount + previousCount)
    }
    val conf = new SparkConf().setMaster("local[2]").setAppName("stateFull")
    val sc = new StreamingContext(conf, Seconds(10))
    sc.checkpoint(".")
    val lines = sc.socketTextStream("127.0.0.1", 9998)
    val words = lines.flatMap(_.split(" "))
    val wordDstream = words.map(x => (x, 1))
    val stateDstream = wordDstream.updateStateByKey[Int](updateFunc)
    stateDstream.print()
    //新增的语句，把DStream保存到文本文件中
    stateDstream.saveAsTextFiles("dstreamoutput/output.txt")
    //下面是新增的语句，把DStream保存到MySQL数据库中
    stateDstream.foreachRDD(rdd => {
      //内部函数
      def func(records: Iterator[(String,Int)]) {
        var conn: Connection = null
        var stmt: PreparedStatement = null
        try {
          val url = "jdbc:mysql://127.0.0.1:3306/spark"
          val user = "root"
          val password = "123456"  //数据库密码
          conn = DriverManager.getConnection(url, user, password)
          records.foreach(p => {
            val sql = "insert into wordcount(word,count) values (?,?)"
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, p._1.trim)
            stmt.setInt(2,p._2.toInt)
            stmt.executeUpdate()
          })
        } catch {
          case e: Exception => e.printStackTrace()
        } finally {
          if (stmt != null) {
            stmt.close()
          }
          if (conn != null) {
            conn.close()
          }
        }
      }
      val repartitionedRDD = rdd.repartition(3)
      repartitionedRDD.foreachPartition(func)
    })
    sc.start()
    sc.awaitTermination()
  }
}