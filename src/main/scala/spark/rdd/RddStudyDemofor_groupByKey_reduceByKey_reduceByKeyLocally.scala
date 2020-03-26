package spark.rdd

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by 邵洋 on 2017/3/23.
  */
object RddStudyDemofor_groupByKey_reduceByKey_reduceByKeyLocally {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("RddStudyDemofor_groupByKey_reduceByKey_reduceByKeyLocally").setMaster("local")
    conf.set("spark.testing.memory", "2147480000")//后面的值大于512m即可
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(Array(("A",1),("B",1),("C",1)))
    val rdd2 = sc.makeRDD(Array(("A",2),("B",2),("C",2)))
    val rdd3 = sc.makeRDD(Array(("A",3),("B",3),("C",3)))
    val rdd4 = sc.makeRDD(Array(("A",4),("B",4),("C",4)))
    val rdd5 = sc.makeRDD(Array(("A",5),("B",5),("C",5)))

    // ("A",(1,2,3,4)),("B",(1,2,3,4)),("C",(1,2,3,4))
    val rdd1groupByKey = rdd1.groupByKey()
    rdd1groupByKey.foreach(println)
//    for ( t <- rdd1groupByKey){
//      println(t)
//    }

    val rdd11 = rdd1.reduceByKey((x,y) => x + y).cache()

    val rdd22 = rdd2.reduceByKey((x,y) => x + y).cache()

    val rdd33 = rdd3.reduceByKey((x,y) => x + y).cache()

    val rdd44 = rdd4.reduceByKey((x,y) => x + y).cache()

    val rdd55 = rdd5.reduceByKey((x,y) => x + y).cache()

//    val rdd = rdd11.union(rdd22).union(rdd33).union(rdd44)
//    val ret = rdd.groupByKey().map(x => {
//      val key = x._1
//      val group = x._2.toArray
//      (key, (group(0), group(1), group(2), group(3)))
//    })
//    ret.foreach(println)

//    val retbyjoin = rdd11.join(rdd22).map(x => {
//      val key = x._1
//      val value = (x._2._1, x._2._2)
//      (key, value)
//    }).join(rdd33).map(x => {
//      val key = x._1
//      val value = (x._2._1._1,x._2._1._2, x._2._2)
//      (key, value)
//    }).join(rdd44).map(x => {
//      val key = x._1
//      val value = (x._2._1._1,x._2._1._2,x._2._1._3, x._2._2)
//      (key, value)
//    }).join(rdd55).map(x => {
//      val key = x._1
//      val value = (x._2._1._1,x._2._1._2,x._2._1._3,x._2._1._4, x._2._2)
//      (key, value)
//    })
//    retbyjoin.foreach(println)

    /*val rdd122 = rdd1.leftOuterJoin(rdd11).collect
    for ( t <- rdd122){
      println("rdd1.leftOuterJoin(rdd11) ="+t)
    }*/

/*    val rdd12 = rdd1.keyBy(_._2).leftOuterJoin(rdd11).collect
    for ( t <- rdd12){
      println("rdd1.keyBy(_._1).leftOuterJoin ="+t)
    }*/

//    rdd1.keyBy(_._1).leftOuterJoin =(0,((A,0),None))
//    rdd1.keyBy(_._1).leftOuterJoin =(2,((A,2),None))
//    rdd1.keyBy(_._1).leftOuterJoin =(2,((B,2),None))
//    rdd1.keyBy(_._1).leftOuterJoin =(1,((B,1),None))
//    rdd1.keyBy(_._1).leftOuterJoin =(1,((C,1),None))

//    rdd1.keyBy(_._1).leftOuterJoin =(0,((A,0),None))
//    rdd1.keyBy(_._1).leftOuterJoin =(2,((A,2),None))
//    rdd1.keyBy(_._1).leftOuterJoin =(2,((B,2),None))
//    rdd1.keyBy(_._1).leftOuterJoin =(1,((B,1),None))
//    rdd1.keyBy(_._1).leftOuterJoin =(1,((C,1),None))

    /*val rdd22 = rdd1.reduceByKey((x,y) => x ).collect
    for ( t <- rdd22){
      println("rdd1.reduceByKey((x,y) => x ) ="+t)
    }*/

    /*val a = sc.parallelize(List((1,2),(1,3),(2,7),(2,5),(3,4),(3,6)))
    val aa = a.reduceByKey((x,y) => x).collect
    for ( t <- aa){
      println("aa.reduceByKey((x,y) => x ) ="+t)
    }*/

    /*val rdd3 = rdd1.reduceByKeyLocally((x,y) => x + y)
    for ( t <- rdd3){
      println(t)
    }*/

    /*val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")
    println("capitals.get( \"France\" ) : " +  capitals.get( "France" ))
    println("capitals.get( \"India\" ) : " +  capitals.get( "India" ))

    println("capitals.get( \"France\" ).get : " +  capitals.get( "France" ).get)
    println("capitals.get( \"India\" ).get : " +  capitals.get( "India" ).get)*/

/*    val map1 = Map("France" -> "Paris", "Japan" -> "Tokyo").toMap
    println(map1.get("France").get)
    println(map1.get("Japan").get)*/
  }

}