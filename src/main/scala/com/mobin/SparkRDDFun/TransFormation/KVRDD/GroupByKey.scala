package com.mobin.SparkRDDFun.TransFormation.KVRDD

import org.apache.spark.{SparkContext, SparkConf}

object GroupByKey {

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("ReduceByKey")
    val sc = new SparkContext(conf)
    val arr = List(("A", 1), ("B", 2), ("A", 2), ("B", 3))
    val rdd = sc.parallelize(arr)
    val groupByKeyRDD = rdd.groupByKey()
    groupByKeyRDD.foreach(println)
    sc.stop
  }
}
