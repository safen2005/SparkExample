package spark.streamtomysql

/**
  * Created by 邵洋 on 2017/3/3.
  */

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object sparkstreamdataToMySQL {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("use the foreachRDD write data to mysql").setMaster("local[2]")
    conf.set("spark.testing.memory", "2147480000")//后面的值大于512m即可
    val ssc = new StreamingContext(conf,Seconds(10))

    val streamData = ssc.socketTextStream("127.0.0.1",9999)
    val wordCount = streamData.map(line =>(line.split(",")(0),1)).reduceByKeyAndWindow(_+_,Seconds(60))
    val hottestWord = wordCount.transform(itemRDD => {
      val top3 = itemRDD.map(pair => (pair._2, pair._1))
        .sortByKey(false).map(pair => (pair._2, pair._1)).collect()
      ssc.sparkContext.makeRDD(top3)
    })
    hottestWord.foreachRDD( rdd =>{
      rdd.foreachPartition(partitionOfRecords =>{
        val connect = scalaConnectPool.getConnection
        connect.setAutoCommit(false)
        val stmt = connect.createStatement()
        partitionOfRecords.foreach(record =>{
          println(record._1)
          stmt.addBatch("insert into searchKeyWord (insert_time,keyword,search_count) values (now(),'"+record._1+"','"+record._2+"')")
        })
        stmt.executeBatch()
        connect.commit()
      }
      )
    }
    )
    ssc.start()
    ssc.awaitTermination()
  }
}