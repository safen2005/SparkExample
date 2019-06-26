package com.mobin.SparkRDDFun.TransFormation.KVRDD

import org.apache.spark.{SparkContext, SparkConf}

/**
  * 对[K,V]型数据中的V值flatmap操作
  */
object FlatMapValus {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("map")
    val sc = new SparkContext(conf)
    val list = List(("mobin",22),("kpop",20),("lufei",23))
    val rdd = sc.parallelize(list)
    val mapValuesRDD = rdd.flatMapValues(x => Seq(x,"male"))
    /*(mobin,22)
    (mobin,male)
    (kpop,20)
    (kpop,male)
    (lufei,23)
    (lufei,male)*/
    //val mapValuesRDD = rdd.mapValues(x => Seq(x,"male"))
   /* (mobin,List(22, male))
    (kpop,List(20, male))
    (lufei,List(23, male))*/
    mapValuesRDD.foreach(println)
  }
}
