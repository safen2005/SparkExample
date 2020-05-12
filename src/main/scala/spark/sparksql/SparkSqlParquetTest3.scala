package spark.sparksql

import java.text.SimpleDateFormat
import java.util.{Date, TimeZone}

import org.apache.spark.sql.SparkSession

object SparkSqlParquetTest3 {

  def main(args: Array[String]): Unit = {

    val appName = this.getClass.getSimpleName
    println("appName = "+appName)

    val spark = SparkSession.
      builder().
      appName(appName).
      master("local[2]").
      getOrCreate()

    val df = spark.read.format("parquet").load("E:\\IdeaProjects\\files\\2020-04-13\\*")
    //df.schema.iterator.foreach(println)
    //df.show(5)
    df.createOrReplaceTempView("bgb")
    val bgbdf = spark.sql("select '读写科技产品部' AS b_name, 'unknown' AS p_id, " +
      "'智能办公本' AS p_name, 'unknown' AS p_model, duid AS sn, 'unknown' AS imei, " +
      "'unknown' AS colour, 'unknown' AS size, 'unknown' AS act_ip, '中国' AS act_country, " +
      "province AS act_province, city AS act_city, '中国' AS common_country, " +
      "'unknown' AS common_province, 'unknown' AS common_city, day as act_time FROM bgb WHERE isNew =true")
    val bgblist = bgbdf.collectAsList()
    val totals = bgbdf.collectAsList().size()
    println(totals)
    val pagesize = 500
    val totalsize = math.ceil(totals.toDouble/pagesize).toInt
    println(totalsize)
    //设置日期格式
    val sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00")
    sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
    println(sdf.format(new Date))

//    spark.sql("select count(duid),count(distinct duid) FROM bgb WHERE isNew =true").show()

//    df.registerTempTable("psi")
//    sqlContext.sql("select count(sn) as sn_pv,count(distinct sn) as sn_uv,count(idx) as pv,count(distinct idx) as uv,min(idx) as minidx,max(idx) as maxidx,max(idx_max) from psi group by e_id").show()
//    sqlContext.sql("select e_id,count(idx) as pv,count(distinct idx) as uv,min(idx) as minidx,max(idx) as maxidx,max(idx_max) from psi group by e_id").show()
//    sqlContext.sql("select b_name from psi group by b_name ").show()
//    sqlContext.sql("select act_time from psi order by act_time desc limit 10 ").show()

//    sqlContext.sql("select e_id,count(1) as pv from psi group by e_id").show(200)
//    df.schema.iterator.foreach(println)
//    sqlContext.sql("select os_isCracked,udp,udmap from EventInforevisemodel").show(200)
//    sqlContext.sql("select common_appid from EventInforevisemodel group by common_appid").show()
//    sqlContext.sql("select count(*) from EventInforevisemodel where common_appid = 'D7bc1c8d09'").show()
//    sqlContext.sql("select count(*) from EventInforevisemodel where common_appid = 'D7d8240300'").show()
//    sqlContext.sql("select count(*) from EventInforevisemodel where common_appid = 'V1778b6c85'").show()
//    sqlContext.sql("select count(*) from EventInforevisemodel where common_appid = 'demo111111'").show()
//    sqlContext.sql("select count(*) from EventInforevisemodel where common_appid = 'demo222222'").show()
//    var df = sqlContext.read.format("parquet").load("F:\\IdeaProjects\\daas_stable\\*");
//    df.registerTempTable("EventInforevisemodel2")
//    sqlContext.sql("select count(*) from EventInforevisemodel2").show()
//    sqlContext.sql("select common_appid from EventInforevisemodel2 group by common_appid").show()
//
//    df = sqlContext.read.format("parquet").load("F:\\IdeaProjects\\daas_stable2\\*");
//    df.registerTempTable("EventInforevisemodel3")
//    sqlContext.sql("select count(*) from EventInforevisemodel3").show()
//    sqlContext.sql("select common_appid from EventInforevisemodel3 group by common_appid").show()

//    df = sqlContext.read.format("parquet").load("F:\\IdeaProjects\\etl\\sdkmodel\\hf\\sessioninforevisemodel\\2018-10-11\\*\\stable");
//    df.registerTempTable("sessioninforevisemodelbyday")
//    sqlContext.sql("select common_appid from sessioninforevisemodelbyday group by common_appid").show()

////    df.schema.fieldNames.filter(f=>f.startsWith("dimension_")).foreach(println)
//    val dimension_fieldNames = df.schema.fieldNames.filter(f=>f.startsWith("dimension_"))
////    println(dimension_fieldNames(0))
////    println(dimension_fieldNames(1))
////    println(dimension_fieldNames(2))
//    var cs = ""
//    for (i <- 0 until dimension_fieldNames.length) {
//      println(dimension_fieldNames(i))
//      //cs += dimension_fieldNames(i)+" != '' "
//      cs += "length("+dimension_fieldNames(i)+") !=0 "
//      cs += "and "
//    }
//    cs = cs.substring(0,cs.length-4)
//    if(!"".equals(cs)){
//      cs = "where "+cs
//      println(cs)
//    }
    spark.stop()
  }
}