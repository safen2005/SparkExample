package spark.sparkstreaming

import java.io.PrintWriter
import java.net.ServerSocket

/**
  * Created by 邵洋 on 2019/6/21.
  */
object GenerateChar {
  def generateContext(index : Int) : String = {
    import scala.collection.mutable.ListBuffer
    val charList = ListBuffer[Char]()
    for(i <- 65 to 90)
      charList += i.toChar
    val charArray = charList.toArray
    charArray(index).toString
  }
  def index = {
    import  java.util.Random
    val rdm = new Random
    rdm.nextInt(20)
  }
  def main(args: Array[String]) {
    val listener = new ServerSocket(9999)
    while(true){
      val socket = listener.accept()
      new Thread(){
        override def run() = {
          println("来自 客户端client 连接connected from :"+ socket.getInetAddress)
          val out = new PrintWriter(socket.getOutputStream,true)
          while(true){
            Thread.sleep(500)
            val context = generateContext(index)  //产生的字符是字母表的前七个随机字母
            println(context)
            out.write(context + '\n')
            out.flush()
          }
          socket.close()
        }
      }.start()
    }
  }
}
