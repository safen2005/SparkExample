package com.mobin.SparkRDDFun.TransFormation.KVRDD

import org.apache.spark.{HashPartitioner, SparkContext, SparkConf}

/**
  * 统计男性和女生的个数，并以（性别，（名字，名字....），个数）的形式输出
  */
object CombineByKey {

  def main(args: Array[String]) {
    /*
    def createCombine = (x: String) => (x, 1)
    def mergerValue = (peo: (String, Int), x: String) => (peo._1+","+x, peo._2 + 1)
    def mergeCombine = (sex1: (String, Int), sex2: (String, Int)) => (sex1._1 +","+ sex2._1, sex1._2 + sex2._2)*/
    val conf = new SparkConf().setMaster("local").setAppName("combinByKey")
    val sc = new SparkContext(conf)
    val people = List(("male", "Mobin"),("male", "Kpop"),
      ("female", "Lucy2"),("female", "Lucy"),
      ("male", "Lufei"), ("female", "Amy"))
    val rdd = sc.parallelize(people,3)
    val combinByKeyRDD = rdd.combineByKey(
      (x: String) => (List(x), 3),
      (peo: (List[String], Int), x: String) => (x :: peo._1, peo._2 + 2),
      (sex1: (List[String], Int), sex2: (List[String], Int)) => (sex1._1 ::: sex2._1, sex1._2 + sex2._2))

    combinByKeyRDD.foreach(println)

    /**
      * (1) ShuffledRDD[1] at combineByKey at CombineByKey.scala:20 []
      * +-(1) ParallelCollectionRDD[0] at parallelize at CombineByKey.scala:19 []
      */
    sc.stop()
  }
}
