package com.mobin.scala

object TestArray {

  def main(args: Array[String]) {
    var myList = Array(1.9, 2.9, 3.4, 3.5)
    // 输出所有数组元素
    for ( x <- myList ) {
      println( x )
    }
    // 计算数组所有元素的总和
    var total = 0.0;
    for ( i <- 0 to (myList.length - 1)) {
      total += myList(i);
    }
    println("总和为 " + total);
    // 查找数组中的最大元素
    var max = myList(0);
    for ( i <- 1 to (myList.length - 1) ) {
      if (myList(i) > max) max = myList(i);
    }
    println("最大值为 " + max);

    // 创建矩阵
    var myMatrix = Array.ofDim[Int](3,3)
    for (i <- 0 to 2) {
      for ( j <- 0 to 2) {
        myMatrix(i)(j) = j
      }
    }
    // 打印二维阵列
    for (i <- 0 to 2) {
      for ( j <- 0 to 2) {
        print(" " + myMatrix(i)(j));
      }
      println();
    }

    //合并数组
    var myList1 = Array(1.9, 2.9, 3.4, 3.5)
    var myList2 = Array(8.9, 7.9, 0.4, 1.5)
    var myList3 =  Array.concat( myList1, myList2)
    // 输出所有数组元素
    for ( x <- myList3 ) {
      println( x )
    }
  }
}