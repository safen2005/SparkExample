package com.mobin.SparkRDDFun.TransFormation.KVRDD

import org.apache.spark.{SparkConf, SparkContext}
import org.joda.time.DateTime

object SortByKey {

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[2]").setAppName("ReduceByKey")
    val sc = new SparkContext(conf)
    val arr = List(("A",1),("B",1),("A",1),("AB",1),("AB",1),("ABC",1),("ABC",1),("B",1),("C",1),("D",1),("C",1),("D",1),("T",1),("G",1))
//    val rdd = sc.parallelize(arr,2)
//    println(rdd.count())
//    val arr2 = List(("A",1),("B",1),("A",1),("AB",1),("AB",1),("ABC",1),("ABC",1),("B",1),("C",1),("D",1),("C",1),("D",1),("T",1),("G",1))
//    val rdd2 = sc.parallelize(arr2,2)
//    println(rdd2.count())
//    println(rdd.union(rdd2).count())
//    println(rdd.union(rdd2).distinct().count())

//    val arr = List(("A",1),("A",2),("A",3),("A",4))
//    val rdd = sc.parallelize(arr,2)
//
//    val arr2 = List(("B",5),("B",6))
//    val rdd2 = sc.parallelize(arr2,2)
//
//    rdd.leftOuterJoin(rdd2).foreach(println)

//    val indexRdd = rdd
//      .map(r=>r._1)
//      .distinct()
//      .sortBy(r => r)
//      .zipWithUniqueId()
//      .map(a => (a._1, a._2+1)).cache()
//    indexRdd.foreach(println)
//    //保存索引文件
//    val indexRddSave = indexRdd.map(t => t.productIterator.mkString("\t")).cache()
//    val cnt = indexRddSave.count()
//    println("最大索引值="+cnt)
//
//    val arr2 = List(("Z",1),("H",1),("Z",1),("H",1),("A",1),("B",1),("A",1),("AB",1),("AB",1),("ABC",1),("ABC",1),("B",1),("C",1),("D",1),("C",1),("D",1),("T",1),("G",1))
//    val rdd2 = sc.parallelize(arr2,2)
//    val latestIndexRdd = indexRddSave.map(r=>{(r.split("\t")(0),r.split("\t")(1))})
//    val cnt2 = latestIndexRdd.count()
//    println("latestIndexRdd最大索引值="+cnt2)
//
//    rdd2.leftOuterJoin(latestIndexRdd)
//      .filter(r=>{r._2._2==None})
//      .map(r=>r._1).distinct()
//      .sortBy(r => r)
//      .zipWithUniqueId()
//      .map(a => (a._1, a._2+1+cnt2)).foreach(println)

//    val indexRdd2 = rdd.map(r=>r._1)
//      .distinct()
//      .sortBy(r => r)
//      .zipWithUniqueId()
//      .map(a => (a._1, (a._2+1+cnt))).cache()
//    indexRdd2.foreach(println)

//    println("合并最大索引值="+(indexRdd++indexRdd2).map(t => t.productIterator.mkString("\t")).cache().count())

//    println(indexRdd2.map(r=>{r._2}).collect().takeRight(1)(0))

//    rdd.leftOuterJoin(indexRdd).filter(r=>r._2._2!=None).map(r=>{
//            (r._1,showCapital(r._2._2))
//          }).foreach(println)

//    val sortByKeyRDD = rdd.sortByKey()
//    sortByKeyRDD.foreach(println)

    val rdd = sc.makeRDD(Seq("A","A","A","C","E","E","E","D","B","B","B","B"),2)
    rdd.sortBy(r=>r).zipWithUniqueId().foreach(println)
    rdd.distinct().sortBy(r=>r).zipWithUniqueId().map(a => (a._1, a._2)).foreach(println)

//    val rdd2 = sc.makeRDD(Seq("H","G","F","Z","T"),2)
//    val rdd22 = rdd2.sortBy(r=>r).zipWithIndex()
//      .map(a => (a._1, a._2 + 5))

//    val rdd3 = sc.makeRDD(Seq("A","C","E","D","B"),2)
//    val rdd32 = rdd3.sortBy(r=>r).zipWithIndex()
//      .map(a => (a._1, a._2 + 10))

//    val rdd4 = rdd12.union(rdd22)
//    //rdd4.cogroup(rdd32).foreach(println)

//    rdd4.leftOuterJoin(rdd32).filter(r=>r._2._2!=None).map(r=>{
//      ((r._1,r._2._1),showCapital(r._2._2))
//    }).map(t => t.productIterator.mkString("\t")).map(_.split("\t"))
//      .map(a=> (a(0), a(1))).foreach(println)

//    println(new DateTime(1567920375575L).toString("yyyy-MM-dd"))

    sc.stop
  }

  def showCapital(x: Option[Long]) = x match {
    case Some(s) => s
    case None => "?"
  }
}
