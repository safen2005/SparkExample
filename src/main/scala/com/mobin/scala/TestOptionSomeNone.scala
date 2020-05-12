package com.mobin.scala

object TestOptionSomeNone {
  //柯里化
  def mul(x: Int, y: Int) = x * y
  def mulCurry(x: Int)=(y: Int) => x * y
  def mulCurry2(x: Int)(y:Int) = x * y

  def main(args: Array[String]): Unit = {
//    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo", "China" -> "Beijing")
//    println(capitals.get("France").getOrElse(""))

    println(mul(10, 10))
    println(mulCurry(10)(9))
    println(mulCurry2(10)(8))

    val a = Array("Hello", "World")
    val b = Array("hello", "world")
    println(a.corresponds(b)(_.equalsIgnoreCase(_)))
  }
}