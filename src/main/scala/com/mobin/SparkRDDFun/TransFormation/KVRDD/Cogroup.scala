package com.mobin.SparkRDDFun.TransFormation.KVRDD

import org.apache.spark.{SparkContext, SparkConf}

object Cogroup {

  /**
    * .cogroup(otherDataSet，numPartitions)：
    * 对两个RDD(如:(K,V)和(K,W))相同Key的元素先分别做聚合，
    * 最后返回(K,Iterator<V>,Iterator<W>)形式的RDD,numPartitions
    * 设置分区数，提高作业并行度
    * @param args
    */
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("ReduceByKey")
    val sc = new SparkContext(conf)
    val arr = List(("A", 1), ("B", 2), ("A", 2), ("B", 3),("C",5))
    val arr1 = List(("A", "A1"), ("B", "B1"), ("A", "A2"), ("B", "B2"), ("B", "B3"))
    val rdd = sc.parallelize(arr, 3)
    val rdd1 = sc.parallelize(arr1, 3)
    val groupByKeyRDD = rdd.cogroup(rdd1)
    groupByKeyRDD.foreach(println)
    sc.stop
  }
}
