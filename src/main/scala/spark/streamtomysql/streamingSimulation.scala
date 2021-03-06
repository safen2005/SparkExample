package spark.streamtomysql

import java.io.{PrintWriter}
import java.net.ServerSocket
import scala.io.Source

object streamingSimulation {
  def index(n: Int) = scala.util.Random.nextInt(n)

  def main(args: Array[String]) {
    // 调用该模拟器需要三个参数，分为为文件路径、端口号和间隔时间（单位：毫秒）
    //    if (args.length != 3) {
    //      System.err.println("Usage: <filename> <port> <millisecond>")
    //      System.exit(1)
    //    }

    // 获取指定文件总的行数
    //    val filename = args(0)
    val filename = "E:\\IdeaProjects\\files\\people.txt"
    val lines = Source.fromFile(filename).getLines.toList
    val filerow = lines.length

    // 指定监听某端口，当外部程序请求时建立连接
    //val listener = new ServerSocket(args(1).toInt)//9999
    val listener = new ServerSocket(9999)
    while (true) {
      val socket = listener.accept()
      new Thread() {
        override def run = {
          println("Got client connected from: " + socket.getInetAddress)
          val out = new PrintWriter(socket.getOutputStream(), true)
          while (true) {
            //Thread.sleep(args(2).toLong)
            Thread.sleep(2000)
            // 当该端口接受请求时，随机获取某行数据发送给对方
            val content = lines(index(filerow))
            println("-------------------------------------------")
            println(s"Time: ${System.currentTimeMillis()}")
            println("-------------------------------------------")
            println(content)
            out.write(content + '\n')
            out.flush()
          }
          socket.close()
        }
      }.start()
    }
  }

}