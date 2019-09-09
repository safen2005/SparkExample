package com.mobin.SparkRDDFun.TransFormation.KVRDD

import org.apache.spark.{SparkContext, SparkConf}

object SortByKey {

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("ReduceByKey")
    val sc = new SparkContext(conf)
//    val arr = List(("A",1),("B",2),("A",2),("B",3),("x",1),("e",0))
//    val rdd = sc.parallelize(arr)
//    val sortByKeyRDD = rdd.sortByKey()
//    sortByKeyRDD.foreach(println)

    val rdd = sc.makeRDD(Seq("A","C","E","D","B"),2)
    val rdd12 = rdd.sortBy(r=>r).zipWithIndex().map(a => (a._1, a._2))

    val rdd2 = sc.makeRDD(Seq("H","G","F","Z","T"),2)
    val rdd22 = rdd2.sortBy(r=>r).zipWithIndex()
      .map(a => (a._1, a._2 + 5))

    val rdd3 = sc.makeRDD(Seq("A","C","E","D","B"),2)
    val rdd32 = rdd3.sortBy(r=>r).zipWithIndex()
      .map(a => (a._1, a._2 + 10))

    val rdd4 = rdd12.union(rdd22)
    //rdd4.cogroup(rdd32).foreach(println)

    rdd4.leftOuterJoin(rdd32).filter(r=>r._2._2!=None).map(r=>{
      (r._1,r._2._1+" and "+showCapital(r._2._2))
    }).foreach(println)

    sc.stop
  }

  def showCapital(x: Option[Long]) = x match {
    case Some(s) => s
    case None => "?"
  }
}
