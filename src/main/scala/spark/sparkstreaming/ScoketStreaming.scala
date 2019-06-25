package spark.sparkstreaming

/**
  * Created by 邵洋 on 2019/6/20.
  */
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ScoketStreaming {
  def main(args: Array[String]) {
    val appname = this.getClass.getSimpleName
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName(appname)
      .getOrCreate()
    val sc = spark.sparkContext
    //每隔10秒统计一次字符总数
    val ssc = new StreamingContext(sc,Seconds(10))
    //创建珍一个DStream，连接127.0.0.1:9998
    val lines = ssc.socketTextStream("127.0.0.1",9998)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x , 1)).reduceByKey(_ + _)
    wordCounts.print()
    ssc.start()         //开始计算
    ssc.awaitTermination()   //通过手动终止计算，否则一直运行下去
  }
}
