package spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by 邵洋 on 2017/3/23.
  */
object RddStudyDemoforCogroupandJoin {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("RddStudyDemoforCogroupandJoin").setMaster("local")
    conf.set("spark.testing.memory", "2147480000")//后面的值大于512m即可
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(Array(("A","1"),("B","2"),("C","3")),2)
    val rdd2 = sc.makeRDD(Array(("A","a"),("C","c"),("D","d")),2)
    val rdd3 = sc.makeRDD(Array(("A","A"),("E","E")),2)
    //cogroup相当于SQL中的全外关联full outer join，返回左右RDD中的记录，关联不上的为空
    val rdd4 = rdd1.cogroup(rdd2,rdd3).sortByKey()
    for ( t <- rdd4){
      println(t)
    }

    var rdd5 = rdd1.join(rdd2)
    for ( t <- rdd5){
      println(t)
    }

    val rdd55 = rdd1.cogroup(rdd2)
    rdd55.map(r=>{
      if(r._2._1.toList.size>0){
        r._2._1.toList(0)
      }
      }).foreach(println)
    for ( t <- rdd55){
      println(t)
    }

    rdd5 = rdd2.join(rdd1)
    for ( t <- rdd5){
      println(t)
    }

    var rdd6 = rdd5.join(rdd3)
    for ( t <- rdd6){
      println(t)
    }

  }

}
