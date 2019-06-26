package com.mobin.SparkRDDFun.TransFormation.BaseRDD

import org.apache.spark.{SparkConf, SparkContext}

/**
  * mapPartitions:类似与map，map作用于每个分区的每个元素，但mapPartitions作用于每个分区
  * mapPartitionsWithIndex:将[mapPartitionsWithIndex]注释部分去掉即是
  * 与mapPartitions类似，不同的时函数多了个分区索引的参数
  */
object MapPartitions {
 // 输出有女性的名字：
  def partitionsFun(/*[mapPartitionsWithIndex]  index : Int,*/iter : Iterator[(String,String)]) : Iterator[String] = {
    var woman = List[String]()
    while (iter.hasNext){
      val next = iter.next()
      next match {
        case (_,"female") => woman = /*[mapPartitionsWithIndex]"["+index+"]"+*/next._1 :: woman
        //case (_,"female") => woman =  next._1.toList .:: (woman)  错误写法
        case _ =>
      }
    }
    return  woman.iterator
  }


  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("flatmap")
    val sc = new SparkContext(conf)
    val l = List(("kpop","female"),("zorro","male"),("mobin","male"),("lucy","female"))
    val rdd = sc.parallelize(l,2)
   // val mp = rdd.mapPartitions(x => x.filter(_._2 == "female")).map(x => x._1)
    val mp = rdd.mapPartitions(partitionsFun)
    //[mapPartitionsWithIndex]  val mp = rdd.mapPartitionsWithIndex(partitionsFun)
    mp.collect.foreach(x => (print(x +" "))) //将分区中的元素转换成Aarray再输出
  }
}
