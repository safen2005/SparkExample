package com.mobin.sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by 邵洋 on 6/20/19.
  */
object ScoketStreamingOther {

  def main(args: Array[String]){
    val conf = new SparkConf().setMaster("local[2]").setAppName("ScoketStreaming")
    val sc = new StreamingContext(conf,Seconds(10))

    val lines = sc.socketTextStream("127.0.0.1",9998)
    val words = lines.flatMap(_.split((" ")))
    val wordCounts = words.map(x => (x , 1)).reduceByKey(_ + _)

    val wordCountsOther = words.map(x => (x , 2)).reduceByKey(_ + _)

//    wordCounts.union(wordCountsOther).print()
//    wordCounts.join(wordCountsOther).print()
//    wordCounts.cogroup(wordCountsOther).print()
//    wordCounts.countByValue().print()

    sc.start()
    sc.awaitTermination()
  }
}
