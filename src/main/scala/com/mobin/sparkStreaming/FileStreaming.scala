package com.mobin.sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by 邵洋 on 6/20/19.
  */
object FileStreaming {
  def main(args: Array[String]) {
    //设置为本地运行模式，2个线程，一个监听，另一个处理数据
    val conf = new SparkConf().
      setMaster("local[2]").
      setAppName("FileStreaming")
    val sc = new StreamingContext(conf,Seconds(5))// 时间间隔为5秒
    //这里采用本地文件，也可以采用HDFS文件
    val lines = sc.textFileStream("file:///E:\\IdeaProjects15\\SparkExample\\word")
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x , 1)).
                  reduceByKey(_ + _)
    wordCounts.print()
    sc.start()
    sc.awaitTermination()
  }
}
