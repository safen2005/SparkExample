package com.mobin.java;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.joda.time.DateTime;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

public class JavaTest3 {

    public static void setValue(String s){
        s = "新";
        System.out.println("1:"+s);
    }
    public static void main(String[] args)throws Exception{
//        String s = "旧";
//        setValue(s);
//        System.out.println("2:"+s);

//        int num = 10;
//        int singleNum = 10;
//        int n = 1;
//        if(num>=singleNum){
//            n = (num/singleNum);
//            if(num%singleNum!=0){
//                n=n+1;
//            }
//        }
//        System.out.println(n);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        long dt_day = sdf.parse(sdf.format(new Date(1573167555110L))).getTime();
//        System.out.println(dt_day);
//        System.out.println(sdf.format(new Date(dt_day)));
//
//        SimpleDateFormat sdf_day = new SimpleDateFormat("yyyy-MM-dd");
//        sdf_day.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        System.out.println(sdf_day.format(new Date()));
//        System.out.println(new DateTime(sdf_day.format(new Date())).plusDays(-2).toString("yyyy-MM-dd"));

//        long dt = sdf_day.parse(sdf_day.format(new Date(1573167555110L))).getTime();
//        System.out.println(dt);
//        System.out.println(sdf_day.format(new Date(dt)));
//
//        SimpleDateFormat sdf_hour = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
//        sdf_hour.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        long hr =sdf_hour.parse(sdf_hour.format(new Date(1573167555110L))).getTime();
//        System.out.println(hr);
//        System.out.println(sdf_hour.format(new Date(hr)));
//
//        System.out.println(dt_day == dt);
//        System.out.println(dt_day == hr);
//        System.out.println(dt == hr);

//        File ipFile = new File("ip.datx");
//        int contentLength = Long.valueOf(ipFile.length()).intValue();
//        System.out.println(contentLength);
//
//        String ss ="mid,eid,etype,dur,udmap,sid,udp,did,uid,is_new_did,is_new_uid,duid,mac,imei,imsi,android_id,unique_id,openudid,idfa,uuid,user_cookie,device_brand,device_model,device_resolution,language,os_release,os_isCracked,client_ip,ntt,is_wifi,sub_ntt,operator,lng,lat,common_country,common_province,common_city,appver,channel,app_name,app_pkg,common_appid,sdk_ver,sdk_platform,title,url,url_params,charset,islastpage,referrer,browser,event_ts,client_ts,server_ts,common_ts,common_dt,common_hr";
//        System.out.println(ss.split(",").length);
//        String s3 ="";
//        int i = 1;
//        //ps.setString(11, eventInfoReviseModel.udmap);
//        for(String s : ss.split(",")){
//            s3+="ps.setString("+i+", eventInfoReviseModel."+s+");\n";
//            i++;
//        }
//        System.out.println(s3);
//        ss = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
//        System.out.println(ss.split(",").length);

//        String jsons = "{\n" +
//                "  \"schema\": {\n" +
//                "    \"type\": \"struct\",\n" +
//                "    \"fields\": [\n" +
//                "      {\n" +
//                "        \"type\": \"struct\",\n" +
//                "        \"fields\": [\n" +
//                "          {\n" +
//                "            \"type\": \"int32\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"id\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"first_name\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"last_name\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"email\"\n" +
//                "          }\n" +
//                "        ],\n" +
//                "        \"optional\": true,\n" +
//                "        \"name\": \"mysql-server-1.inventory.customers.Value\",\n" +
//                "        \"field\": \"before\"\n" +
//                "      },\n" +
//                "      {\n" +
//                "        \"type\": \"struct\",\n" +
//                "        \"fields\": [\n" +
//                "          {\n" +
//                "            \"type\": \"int32\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"id\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"first_name\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"last_name\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"email\"\n" +
//                "          }\n" +
//                "        ],\n" +
//                "        \"optional\": true,\n" +
//                "        \"name\": \"mysql-server-1.inventory.customers.Value\",\n" +
//                "        \"field\": \"after\"\n" +
//                "      },\n" +
//                "      {\n" +
//                "        \"type\": \"struct\",\n" +
//                "        \"fields\": [\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"version\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"connector\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"name\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"int64\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"ts_ms\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"boolean\",\n" +
//                "            \"optional\": true,\n" +
//                "            \"default\": false,\n" +
//                "            \"field\": \"snapshot\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"db\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": true,\n" +
//                "            \"field\": \"table\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"int64\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"server_id\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": true,\n" +
//                "            \"field\": \"gtid\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"file\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"int64\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"pos\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"int32\",\n" +
//                "            \"optional\": false,\n" +
//                "            \"field\": \"row\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"int64\",\n" +
//                "            \"optional\": true,\n" +
//                "            \"field\": \"thread\"\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"string\",\n" +
//                "            \"optional\": true,\n" +
//                "            \"field\": \"query\"\n" +
//                "          }\n" +
//                "        ],\n" +
//                "        \"optional\": false,\n" +
//                "        \"name\": \"io.debezium.connector.mysql.Source\",\n" +
//                "        \"field\": \"source\"\n" +
//                "      },\n" +
//                "      {\n" +
//                "        \"type\": \"string\",\n" +
//                "        \"optional\": false,\n" +
//                "        \"field\": \"op\"\n" +
//                "      },\n" +
//                "      {\n" +
//                "        \"type\": \"int64\",\n" +
//                "        \"optional\": true,\n" +
//                "        \"field\": \"ts_ms\"\n" +
//                "      }\n" +
//                "    ],\n" +
//                "    \"optional\": false,\n" +
//                "    \"name\": \"mysql-server-1.inventory.customers.Envelope\"\n" +
//                "  },\n" +
//                "  \"payload\": {\n" +
//                "    \"op\": \"c\",\n" +
//                "    \"ts_ms\": 1465491411815,\n" +
//                "    \"before\": null,\n" +
//                "    \"after\": {\n" +
//                "      \"id\": 1004,\n" +
//                "      \"first_name\": \"Anne\",\n" +
//                "      \"last_name\": \"Kretchmar\",\n" +
//                "      \"email\": \"annek@noanswer.org\"\n" +
//                "    },\n" +
//                "    \"source\": {\n" +
//                "      \"version\": \"0.10.0.Final\",\n" +
//                "      \"connector\": \"mysql\",\n" +
//                "      \"name\": \"mysql-server-1\",\n" +
//                "      \"ts_ms\": 0,\n" +
//                "      \"snapshot\": false,\n" +
//                "      \"db\": \"inventory\",\n" +
//                "      \"table\": \"customers\",\n" +
//                "      \"server_id\": 0,\n" +
//                "      \"gtid\": null,\n" +
//                "      \"file\": \"mysql-bin.000003\",\n" +
//                "      \"pos\": 154,\n" +
//                "      \"row\": 0,\n" +
//                "      \"thread\": 7,\n" +
//                "      \"query\": \"INSERT INTO customers (first_name, last_name, email) VALUES ('Anne', 'Kretchmar', 'annek@noanswer.org')\"\n" +
//                "    }\n" +
//                "  }\n" +
//                "}";
//
//        JSONObject jb= JSONObject.parseObject(jsons);
//        System.out.println(jb);
//        System.out.println(jb.getJSONObject("payload"));
//        System.out.println(jb.getJSONObject("payload").getString("op"));
//        System.out.println(jb.getJSONObject("payload").getJSONObject("source").getString("query"));

//        String ss="`id` String, `mid` String, `eid` String, `error_name` String,`error_position` String,`label` String,`etype` String, `dur` Int64,`idx` Int64, `udmap` String, `source` String,`msg` String,`sid` String, `udp` String, `did` String, `uid` String, `is_new_did` UInt8, `is_new_uid` UInt8, `duid` String, `mac` String, `imei` String, `imsi` String, `android_id` String, `unique_id` String, `openudid` String, `idfa` String, `uuid` String, `user_cookie` String, `device_brand` String, `device_model` String, `device_resolution` String, `language` String, `os_release` String, `os_isCracked` UInt8, `client_ip` String, `ntt` String, `is_wifi` UInt8, `sub_ntt` String, `operator` String, `lng` String, `lat` String, `appvercode` String, `process` String,`index_time` Int64, `common_country` String, `common_province` String, `common_city` String, `appver` String, `channel` String, `app_name` String, `app_pkg` String, `common_appid` String, `sdk_ver` String, `sdk_platform` String, `title` String, `url` String, `url_params` String, `charset` String, `islastpage` UInt8, `referrer` String, `browser` String, `event_ts` Int64, `client_ts` Int64, `server_ts` Int64, `common_ts` Int64, `common_dt` Int64, `common_hr`";
//        System.out.println(ss.split(",").length);
//        ss = ss.replace("`","");
//        ss = ss.replace(" String","");
//        ss = ss.replace(" Int64","");
//        ss = ss.replace(" UInt8","");
//        ss = ss.replace(" ","");
//        System.out.println(ss);
//        System.out.println(ss.split(",").length);
//
//        String s4 ="?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
//        System.out.println(s4.split(",").length);

//        String ss = "id,mid,eid,error_name,error_position,label,etype,dur,idx,udmap,source,msg,sid,udp,did,uid,is_new_did,is_new_uid,duid,mac,imei,imsi,android_id,unique_id,openudid,idfa,uuid,user_cookie,device_brand,device_model,device_resolution,language,os_release,os_isCracked,client_ip,ntt,is_wifi,sub_ntt,operator,lng,lat,appvercode,process,index_time,common_country,common_province,common_city,appver,channel,app_name,app_pkg,common_appid,sdk_ver,sdk_platform,title,url,url_params,charset,islastpage,referrer,browser,event_ts,client_ts,server_ts,common_ts,common_dt,common_hr";
//        System.out.println(ss.split(",").length);
//        String s3 ="";
//        int i = 1;
//        for(String s : ss.split(",")){
//            //s3+="ps.setString("+i+", errorInfoModel."+s+");\n";
//            //errorDetail.put("is_root",is_root);
//            s3+="errorDetail.put(\""+s+"\",errorInfoModel."+s+");\n";
//            i++;
//        }
//        System.out.println(s3);

        //error_type
//        String etypes = "crash,crash_native";
//        String etype = "";
//        if(etypes.split(",").length>1){
//                etype = " and etype in(";
//                for(String type:etypes.split(",")){
//                    etype +="'"+type+"',";
//                }
//                etype = etype.substring(0,etype.length()-1);
//                etype += ") ";
//        }else{
//                etype = " and etype ='"+etypes+"' ";
//        }
//        System.out.println(etype);

//        String js = "{\"ret\":\"0\",\"data\":\"{\\\"total\\\":\\\"[{\\\\\\\"total\\\\\\\":\\\\\\\"3424\\\\\\\"}]\\\",\\\"list\\\":\\\"[{\\\\\\\"eid\\\\\\\":\\\\\\\"fec403af5eb5fc1a0cf48d9c6d2c6cea\\\\\\\",\\\\\\\"uv\\\\\\\":\\\\\\\"773\\\\\\\",\\\\\\\"pv\\\\\\\":\\\\\\\"894\\\\\\\",\\\\\\\"maxts\\\\\\\":\\\\\\\"2019-12-09 14:55:08\\\\\\\"},{\\\\\\\"eid\\\\\\\":\\\\\\\"db8f2561855a64ff03035d8f5c74cf40\\\\\\\",\\\\\\\"uv\\\\\\\":\\\\\\\"286\\\\\\\",\\\\\\\"pv\\\\\\\":\\\\\\\"795\\\\\\\",\\\\\\\"maxts\\\\\\\":\\\\\\\"2019-12-09 15:09:50\\\\\\\"},{\\\\\\\"eid\\\\\\\":\\\\\\\"f827a6437d1cd48c6a75c4a902066b61\\\\\\\",\\\\\\\"uv\\\\\\\":\\\\\\\"75\\\\\\\",\\\\\\\"pv\\\\\\\":\\\\\\\"781\\\\\\\",\\\\\\\"maxts\\\\\\\":\\\\\\\"2019-12-09 11:39:16\\\\\\\"},{\\\\\\\"eid\\\\\\\":\\\\\\\"9c1abd8bd2eab4610bb83488999d04b7\\\\\\\",\\\\\\\"uv\\\\\\\":\\\\\\\"70\\\\\\\",\\\\\\\"pv\\\\\\\":\\\\\\\"438\\\\\\\",\\\\\\\"maxts\\\\\\\":\\\\\\\"2019-12-09 08:11:13\\\\\\\"},{\\\\\\\"eid\\\\\\\":\\\\\\\"7029eb5cc5aaf3a4de47ea66c93e521b\\\\\\\",\\\\\\\"uv\\\\\\\":\\\\\\\"60\\\\\\\",\\\\\\\"pv\\\\\\\":\\\\\\\"363\\\\\\\",\\\\\\\"maxts\\\\\\\":\\\\\\\"2019-12-09 15:15:49\\\\\\\"}]\\\"}\"}";
//        JSONObject jsb = JSONObject.parseObject(js);
//        System.out.println(jsb.getJSONObject("data").getJSONArray("total").getJSONObject(0).getString("total"));
//        System.out.println(jsb.getJSONObject("data").getJSONArray("list").getJSONObject(0).getString("eid"));

//        List rsList = new LinkedList<JSONObject>();
//        System.out.println(rsList.size());
//        String s1 = "ABc";
//        String s2 = "abC";
//        System.out.println(s1.equalsIgnoreCase(s2));
//        System.out.println(s2.equalsIgnoreCase(s1));

//        int i =1;
//        for(int j=0;j<10;j++){
//            System.out.println(i++);
//        }

//        File file = new File("iflyrec.properties");//定义一个file对象，用来初始化FileReader
//        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
//        BufferedReader bReader = new BufferedReader( new InputStreamReader(new FileInputStream(file),"UTF-8") );//new一个BufferedReader对象，将文件内容读取到缓存
//
//        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
//        String s = "";
//        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//            //sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
//            //System.out.println(s.substring(0,s.indexOf("="))+" "+s.substring(s.lastIndexOf("from ")+5));
//            System.out.println("/project/daas/daas/data_warehouse/db/ods/"+s.substring(s.lastIndexOf("from ")+5));
//            //System.out.println(s);
//        }
//        bReader.close();
//        String str = sb.toString();
//        System.out.println(str );
        double d1=249639;
        int d2=4900;
        System.out.println((int)Math.ceil(d1/d2));
    }
}