package com.mobin.sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
  * 队列流
  * Created by 邵洋 on 4/6/19.
  */
object QueueStream {

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[2]").setAppName("queueStream")
    //每隔1秒创建一个RDD，Streaming每隔2秒就对数据进行处理
    val ssc = new StreamingContext(conf,Seconds(2))
    val rddQueue = new mutable.SynchronizedQueue[RDD[Int]]()
    val inputStream = ssc.queueStream(rddQueue)
    val mappedStream = inputStream.map(x => (x % 10,1))
    val reduceStream = mappedStream.reduceByKey(_ + _)
    reduceStream.print
    ssc.start()
    for(i <- 1 to 30){
      rddQueue += ssc.sparkContext.makeRDD(1 to 100, 2)
      Thread.sleep(1000)
    }
    ssc.stop()
  }
}
