package spark.sparkstreaming

/**
  * Created by 邵洋 on 2017/3/3.
  */
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ScoketStreaming {
  def main(args: Array[String]) {
    //创建一个本地的StreamingContext，含2个工作线程
    val conf = new SparkConf().setMaster("local[2]").setAppName("ScoketStreaming")
    conf.set("spark.testing.memory", "2147480000")//后面的值大于512m即可
    val sc = new SparkContext(conf)//本地创建 SparkContext

    //val sc = new StreamingContext(conf,Seconds(10))   //每隔10秒统计一次字符总数

    val ssc = new StreamingContext(sc,Seconds(10))   //每隔10秒统计一次字符总数
    //创建珍一个DStream，连接master:9998
    //val lines = sc.socketTextStream("master",9998)
    val lines = ssc.socketTextStream("127.0.0.1",9998)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x , 1)).reduceByKey(_ + _)
    wordCounts.print()
    ssc.start()         //开始计算
    ssc.awaitTermination()   //通过手动终止计算，否则一直运行下去
  }
}
