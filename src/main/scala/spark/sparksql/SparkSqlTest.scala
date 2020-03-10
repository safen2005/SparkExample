package spark.sparksql

import java.util.Properties

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.storage.StorageLevel

object SparkSqlTest {

  def main(args: Array[String]): Unit = {
    val appName = this.getClass.getSimpleName
    println("appName = "+appName)

    val spark = SparkSession.
                builder().
                appName(appName).
                master("local[2]").
                getOrCreate()
        val prop: Properties = new Properties()
        prop.put("driver", "com.mysql.jdbc.Driver")
        prop.put("user", "root")
        prop.put("password", "123456")

//    val predicates =
//      Array(
//        "2019-09-01" -> "2019-09-01 23:59:59",
//        "2019-09-02" -> "2019-09-02 23:59:59",
//        "2019-09-03" -> "2019-09-03 23:59:59",
//        "2019-09-04" -> "2019-09-04 23:59:59"
//      ).map {
//        case (start, end) =>
//          s"cast(CreateTime as date) >= date '$start' " + s"AND cast(CreateTime as date) <= date '$end'"
//      }

    val predicates =
      Array(
        "2015-01-01" -> "2015-03-31 23:59:59",
        "2015-04-01" -> "2015-06-30 23:59:59",
        "2015-07-01" -> "2015-09-30 23:59:59",
        "2015-10-01" -> "2015-12-31 23:59:59",
        "2016-01-01" -> "2016-03-31 23:59:59",
        "2016-04-01" -> "2016-06-30 23:59:59",
        "2016-07-01" -> "2016-09-30 23:59:59",
        "2016-10-01" -> "2016-12-31 23:59:59",
        "2017-01-01" -> "2017-03-31 23:59:59",
        "2017-04-01" -> "2017-06-30 23:59:59",
        "2017-07-01" -> "2017-09-30 23:59:59",
        "2017-10-01" -> "2017-12-31 23:59:59",
        "2018-01-01" -> "2018-03-31 23:59:59",
        "2018-04-01" -> "2018-06-30 23:59:59",
        "2018-07-01" -> "2018-09-30 23:59:59",
        "2018-10-01" -> "2018-12-31 23:59:59",
        "2019-01-01" -> "2019-03-31 23:59:59",
        "2019-04-01" -> "2019-06-30 23:59:59",
        "2019-07-01" -> "2019-09-30 23:59:59",
        "2019-10-01" -> "2019-12-31 23:59:59",
        "2020-01-01" -> "2020-03-31 23:59:59",
        "2020-04-01" -> "2020-06-30 23:59:59",
        "2020-07-01" -> "2020-09-30 23:59:59",
        "2020-10-01" -> "2020-12-31 23:59:59",
        "2021-01-01" -> "2021-03-31 23:59:59",
        "2021-04-01" -> "2021-06-30 23:59:59",
        "2021-07-01" -> "2021-09-30 23:59:59",
        "2021-10-01" -> "2021-12-31 23:59:59",
        "2022-01-01" -> "2022-03-31 23:59:59",
        "2022-04-01" -> "2022-06-30 23:59:59",
        "2022-07-01" -> "2022-09-30 23:59:59",
        "2022-10-01" -> "2022-12-31 23:59:59"
      ).map {
        case (start, end) =>
          s"cast(CreateTime as date) >= date '$start' " + s"AND cast(CreateTime as date) <= date '$end'"
      }

    // 取得该表数据
    val jdbcDF = spark.read.jdbc("jdbc:mysql://127.0.0.1:3306/?useUnicode=true&characterEncoding=UTF-8",
                                  "spark.orderinfo",predicates,prop)
    println(jdbcDF.rdd.partitions.size)
    val jrdd = jdbcDF.rdd.map(r=>{(r.getAs("UserId"),r.getAs("Name"))})
    jrdd.foreach(println)
    jdbcDF.registerTempTable("orderinfo")
    val df = spark.sql("select count(*) from orderinfo")
    df.show()
    //df.write.mode(SaveMode.Overwrite).parquet("orderinfo")

//    val mysql = "(select cast(id as char) as c_id," +
//      "name as c_name," +
//      "gender as c_gender," +
//      "cast(age as char) as c_age," +
//      "cast(dd as char) as c_dd," +
//      "cast(ddtime as char) as c_ddtime," +
//      "cast(is_flag as char) as c_is_flag," +
//      "cast(money as char) as c_money," +
//      "cast(ts as char) as c_ts from spark.student) as query"
//
//    val jdbcDF = spark.read.format("jdbc").
//                option("url","jdbc:mysql://127.0.0.1:3306/?useUnicode=true&characterEncoding=UTF-8").
//                option("driver","com.mysql.jdbc.Driver").
//                option("dbtable", mysql).
//                option("user", "root").
//                option("password", "123456").
////                option("partitionColumn", "id").
////                option("numPartitions", 4).
////                option("lowerBound", 1).
////                option("upperBound", 4).
//                load()
//                .rdd
//                .persist(StorageLevel.MEMORY_ONLY)
//    val keys = jdbcDF
//      .map {
//        Row => (Row.getAs("c_id").toString, Row.getAs("c_name").toString, Row.getAs("c_gender").toString)
//      }
//      .collect()
//
//    println("keys = "+keys)
//    println("keys.size = "+keys.size)
//    var id = ""
//    var name = ""
//    var gender = ""
//    if(keys.size>0){
//      id = keys.apply(0)._1
//      name = keys.apply(0)._2
//      gender = keys.apply(0)._3
//    }
//    println(id+" "+" "+name+" "+gender)
//    val sql = "select concat(id,'') as c_id," +
//      "name as c_name," +
//      "gender as c_gender," +
//      "concat(age,'') as c_age," +
//      "concat(dd,'') as c_dd," +
//      "concat(ddtime,'') as c_ddtime," +
//      "concat(is_flag,'') as c_is_flag," +
//      "concat(money,'') as c_money," +
//      "concat(ts,'') as c_ts from student"

//    jdbcDF.registerTempTable("student")
//    val df = spark.sql(sql)
//    println(df.rdd.partitions.size)
//    df.write.mode(SaveMode.Overwrite).parquet("student")
//
//    spark.read.parquet("student").show()

//    val connectionProperties: Properties = new Properties()
//    connectionProperties.put("driver", "com.mysql.jdbc.Driver")
//    connectionProperties.put("user", "root")
//    connectionProperties.put("password", "123456")
//
//    val dataDF = spark.read.jdbc("jdbc:mysql://127.0.0.1:3306/jeecmsv8f?useUnicode=true&characterEncoding=UTF-8", "searchkeyword", connectionProperties)
//      .select("insert_time","keyword","search_count")
//      .where(s"insert_time > '2019-06-18'")
//    dataDF.registerTempTable("searchkeyword")
//    spark.sql("SELECT insert_time,keyword,search_count FROM searchkeyword WHERE insert_time > '2019-06-18' ORDER BY insert_time DESC").show(40)
//
//    dataDF.rdd.map(row =>(row(0).toString,row(1).toString,row(2).toString)).foreach(println)

    spark.stop()
  }

//  def main(args: Array[String]): Unit = {
//
//    val appName = this.getClass.getSimpleName
//    println("appName = "+appName)
//
//    //是否本地开发模式
//    val isLocal = PropertyConfig().getString("env.type") == "dev"
//    println("isLocal = " + isLocal)
//
//    val conf = new SparkConf().setAppName(appName)
//      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
//      .set("spark.scheduler.minRegisteredResourcesRatio", "0.7")
//      .set("spark.scheduler.maxRegisteredResourcesWaitingTime" , "1200")
//      .set("spark.io.compression.codec","lz4")
//      .set("spark.network.timeout" , "300")
//      .set("spark.reducer.maxSizeInFlight" , "128m")
//      .set("spark.shuffle.io.retryWait","60s")
//      .set("spark.shuffle.io.maxRetries","10")
//      .set("spark.yarn.maxAppAttempts","0")
//      .set("spark.yarn.executor.memoryOverhead","2048")
//    if(isLocal)
//      conf.setMaster("local")
//    conf.registerKryoClasses(Array(classOf[SessionInfoReviseModel]))
//
//    val sc = new SparkContext(conf)
//    sc.setLogLevel("WARN")
//
//    val sqlContext = new SQLContext(sc)
//
//    //输入输出模型组件初始化
//    EtlConstants.IoOutJobInit(sc)
//
//    val s_path0 = "F:\\IdeaProjects\\etl\\sdkmodel\\hf\\sessioninforevisemodel\\2018-10-11\\00\\stable"
//    val s_path2 = "F:\\IdeaProjects\\etl\\sdkmodel\\hf\\sessioninforevisemodel\\2018-10-11\\02\\stable"
//    val s_path4 = "F:\\IdeaProjects\\etl\\sdkmodel\\hf\\sessioninforevisemodel\\2018-10-11\\04\\stable"
//
//    val sdklogpath = PathUtils.flatAllPath(s_path0+","+s_path2+","+s_path4)
//    println("有数据的sdk 路径 = "+sdklogpath)
//
//    val sessionRddbyhour0 = sc.newAPIHadoopFile(s_path0,
//      classOf[ParquetInputFormat[SessionInfoReviseModel]],
//      classOf[Void],
//      classOf[SessionInfoReviseModel],
//      EtlConstants.sireviseInputJob.getConfiguration).cache()
//    println("sessionRddbyhour0.collect() = "+sessionRddbyhour0.collect().size)
//
//    val sessionRddbyhour2 = sc.newAPIHadoopFile(s_path2,
//      classOf[ParquetInputFormat[SessionInfoReviseModel]],
//      classOf[Void],
//      classOf[SessionInfoReviseModel],
//      EtlConstants.sireviseInputJob.getConfiguration).cache()
//    println("sessionRddbyhour2.collect() = "+sessionRddbyhour2.collect().size)
//
//    val sessionRddbyhour4 = sc.newAPIHadoopFile(s_path4,
//      classOf[ParquetInputFormat[SessionInfoReviseModel]],
//      classOf[Void],
//      classOf[SessionInfoReviseModel],
//      EtlConstants.sireviseInputJob.getConfiguration).cache()
//    println("sessionRddbyhour4.collect() = "+sessionRddbyhour4.collect().size)
//
//    println("0 2 4 union collect() = "+sessionRddbyhour0.union(sessionRddbyhour2).union(sessionRddbyhour4).collect().size)
//
//    val sessionRddbyhour = sc.newAPIHadoopFile(sdklogpath,
//      classOf[ParquetInputFormat[SessionInfoReviseModel]],
//      classOf[Void],
//      classOf[SessionInfoReviseModel],
//      EtlConstants.sireviseInputJob.getConfiguration).cache()
//    println("sessionRddbyhour.collect() = "+sessionRddbyhour.collect().size)
//
//    println("sqlContext.read.parquet(s_path0).rdd.collect().size = "+sqlContext.read.parquet(s_path0).rdd.collect().size)
//  }

}