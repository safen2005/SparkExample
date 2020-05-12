package com.mobin.SparkRDDFun.TransFormation.KVRDD

import org.apache.spark.{HashPartitioner, SparkContext, SparkConf}

/**
  * 对[K,V]型数据中的V值map操作
  */
object MapValues {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("map")
    val sc = new SparkContext(conf)
    val list = List(("mobin",22),("kpop",20),("lufei",23),("lufei21",21),("lufei24",24))
    val rdd = sc.parallelize(list,2)
    println(rdd.collect().length)
    val rdd_map = new java.util.HashMap[String,Int]
    rdd.collect().map(x=>{
      rdd_map.put(x._1,x._2)
    })
    import java.util
    //将Map集合中的映射关系取出，出入到Set集合中//将Map集合中的映射关系取出，出入到Set集合中
    val es = rdd_map.entrySet
    val it2 = es.iterator
    while ( {
      it2.hasNext
    }) {
      val mey = it2.next
      //getKey()和getValue是接口Map.Entry<K,V>中的方法，返回对应的键和对应的值
      val key = mey.getKey
      val value = mey.getValue
      System.out.println(key + ":" + value)
    }
    val mapValuesRDD = rdd.mapValues(x => Seq(x,"male"))
    mapValuesRDD.foreach(println)
  }
}
