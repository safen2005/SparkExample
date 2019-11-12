package spark.sparksql

import java.util.Properties

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Row, SQLContext, SparkSession}
/**
  * Created by 邵洋 on 2017/3/2.
  */

object SparkSQLToSQLServerDemo {

  def main(args: Array[String]) {
    val appname = this.getClass.getSimpleName
    val spark=SparkSession.builder().appName(appname).master("local[2]").getOrCreate()
    //使支持RDDs转换为DataFrames及后续sql操作
    import spark.implicits._

    //下面我们设置两条数据表示两个学生信息
    val studentRDD = spark.sparkContext.parallelize(Array("讯飞听见安卓版 1809061820451280 5541.76","讯飞听见iOS版 1905191017200552 6381.0")).map(_.split(" "))
    //下面要设置模式信息
    val schemajdbc = StructType(List(StructField("application_name", StringType, true),StructField("userid", StringType, true),StructField("total", StringType, true)))
    //下面创建Row对象，每个Row对象都是rowRDD中的一行
    val rowRDDjdbc = studentRDD.map(p => Row(p(0).trim, p(1).trim, p(2).trim))
    //建立起Row对象和模式之间的对应关系，也就是把数据和模式对应起来
    val studentDF = spark.createDataFrame(rowRDDjdbc, schemajdbc)

    //下面创建一个prop变量用来保存JDBC连接参数
    val prop = new Properties()
    prop.put("user", "sa") //表示用户名是root
    prop.put("password", "yq1000lmgs1cl!@#") //表示密码是hadoop
    prop.put("driver","net.sourceforge.jtds.jdbc.Driver")
    //下面就可以连接数据库，采用append模式，表示追加记录到数据库spark的student表中
    studentDF.write.mode("append").jdbc("jdbc:jtds:sqlserver://10.103.58.30:1433/IDATA_BI_TEST", "idata_log", prop)

    val jdbcDF = spark.read.format("jdbc").
      option("url","jdbc:jtds:sqlserver://10.103.58.30:1433/IDATA_BI_TEST").
      option("driver","net.sourceforge.jtds.jdbc.Driver").
      option("dbtable", "idata_log").
      option("user", "sa").option("password", "yq1000lmgs1cl!@#").load()

    jdbcDF.show()

    spark.stop()
  }
}