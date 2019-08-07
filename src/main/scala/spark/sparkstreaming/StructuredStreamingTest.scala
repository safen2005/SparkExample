package spark.sparkstreaming

import org.apache.spark.sql.SparkSession

object StructuredStreamingTest {
  def main(args: Array[String]): Unit = {
    //创建Spark SQL切入点
    val spark=SparkSession.builder().appName("Structrued-Streaming").master("local[2]").getOrCreate()
    //读取服务器端口发来的行数据，格式是DataFrame
    val linesDF=spark.readStream.format("socket").option("host","192.168.15.55")
      .option("port",9999).load()
    //隐士转换（DataFrame转DataSet）
    import  spark.implicits._
    //行转换成一个个单词
    val words=linesDF.as[String].flatMap(_.split(" "))
    //按单词计算词频
    val wordCounts=words.groupBy("value").count()

    val query = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()

  }
}
