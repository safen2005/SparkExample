package com.mobin.implicitpackage

class SwingType{
  def  wantLearned(implicit sw : String) = println("兔子已经"+sw)
}
object swimming{
  //隐式转换为目标类型：把一种类型自动转换到另一种类型
  implicit def intToString(x : Int) = x.toString
  implicit def learningType(s : AminalType) = new SwingType
}
class AminalType

object AminalType extends  App{
  import com.mobin.implicitpackage.swimming._
  val rabbit = new AminalType
  rabbit.wantLearned(12)         //蛙泳

  implicit val x = "13"
//  def  wantLearned(implicit sw : String) = println("兔子已经"+sw)
//  wantLearned
  rabbit.wantLearned
}