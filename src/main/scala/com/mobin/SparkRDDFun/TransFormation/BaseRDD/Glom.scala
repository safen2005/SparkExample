package com.mobin.SparkRDDFun.TransFormation.BaseRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 将RDD的每个分区中的类型为T的元素转换换数组Array[T]
  */
object Glom {

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("map")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(1 to 16,4)
    val glomRDD = rdd.glom()   //RDD[Array[T]]
    glomRDD.foreach(rdd => println(rdd.getClass.getSimpleName))
    sc.stop
  }
}
