package spark.sparksql

import java.util.Properties

import org.apache.spark.sql.SparkSession

object SparkSqlTest {

  def main(args: Array[String]): Unit = {
    val appName = this.getClass.getSimpleName
    println("appName = "+appName)

    val spark = SparkSession.
                builder().
                appName(appName).
                master("local").
                getOrCreate()

    val jdbcDF = spark.read.format("jdbc").
                option("url","jdbc:mysql://127.0.0.1:3306/ifly_cpcc_bi_zxdg?useUnicode=true&characterEncoding=UTF-8").
                option("driver","com.mysql.jdbc.Driver").
                option("dbtable", "temp_app_info").
                option("user", "root").
                option("password", "123456").
                load()
    jdbcDF.show()

    val connectionProperties: Properties = new Properties()
    connectionProperties.put("driver", "com.mysql.jdbc.Driver")
    connectionProperties.put("user", "root")
    connectionProperties.put("password", "123456")

    val dataDF = spark.read.jdbc("jdbc:mysql://127.0.0.1:3306/jeecmsv8f?useUnicode=true&characterEncoding=UTF-8", "searchkeyword", connectionProperties)
      .select("insert_time","keyword","search_count")
      .where(s"insert_time > '2019-06-18'")
    dataDF.registerTempTable("searchkeyword")
    spark.sql("SELECT insert_time,keyword,search_count FROM searchkeyword WHERE insert_time > '2019-06-18' ORDER BY insert_time DESC").show(40)

    dataDF.rdd.map(row =>(row(0).toString,row(1).toString,row(2).toString)).foreach(println)

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