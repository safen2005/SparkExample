package com.mobin.java;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        String jsons = "{\n" +
                "  \"schema\": {\n" +
                "    \"type\": \"struct\",\n" +
                "    \"fields\": [\n" +
                "      {\n" +
                "        \"type\": \"struct\",\n" +
                "        \"fields\": [\n" +
                "          {\n" +
                "            \"type\": \"int32\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"id\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"first_name\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"last_name\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"email\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"optional\": true,\n" +
                "        \"name\": \"mysql-server-1.inventory.customers.Value\",\n" +
                "        \"field\": \"before\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"type\": \"struct\",\n" +
                "        \"fields\": [\n" +
                "          {\n" +
                "            \"type\": \"int32\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"id\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"first_name\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"last_name\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"email\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"optional\": true,\n" +
                "        \"name\": \"mysql-server-1.inventory.customers.Value\",\n" +
                "        \"field\": \"after\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"type\": \"struct\",\n" +
                "        \"fields\": [\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"version\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"connector\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"name\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"int64\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"ts_ms\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"boolean\",\n" +
                "            \"optional\": true,\n" +
                "            \"default\": false,\n" +
                "            \"field\": \"snapshot\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"db\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": true,\n" +
                "            \"field\": \"table\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"int64\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"server_id\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": true,\n" +
                "            \"field\": \"gtid\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"file\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"int64\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"pos\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"int32\",\n" +
                "            \"optional\": false,\n" +
                "            \"field\": \"row\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"int64\",\n" +
                "            \"optional\": true,\n" +
                "            \"field\": \"thread\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"string\",\n" +
                "            \"optional\": true,\n" +
                "            \"field\": \"query\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"optional\": false,\n" +
                "        \"name\": \"io.debezium.connector.mysql.Source\",\n" +
                "        \"field\": \"source\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"type\": \"string\",\n" +
                "        \"optional\": false,\n" +
                "        \"field\": \"op\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"type\": \"int64\",\n" +
                "        \"optional\": true,\n" +
                "        \"field\": \"ts_ms\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"optional\": false,\n" +
                "    \"name\": \"mysql-server-1.inventory.customers.Envelope\"\n" +
                "  },\n" +
                "  \"payload\": {\n" +
                "    \"op\": \"c\",\n" +
                "    \"ts_ms\": 1465491411815,\n" +
                "    \"before\": null,\n" +
                "    \"after\": {\n" +
                "      \"id\": 1004,\n" +
                "      \"first_name\": \"Anne\",\n" +
                "      \"last_name\": \"Kretchmar\",\n" +
                "      \"email\": \"annek@noanswer.org\"\n" +
                "    },\n" +
                "    \"source\": {\n" +
                "      \"version\": \"0.10.0.Final\",\n" +
                "      \"connector\": \"mysql\",\n" +
                "      \"name\": \"mysql-server-1\",\n" +
                "      \"ts_ms\": 0,\n" +
                "      \"snapshot\": false,\n" +
                "      \"db\": \"inventory\",\n" +
                "      \"table\": \"customers\",\n" +
                "      \"server_id\": 0,\n" +
                "      \"gtid\": null,\n" +
                "      \"file\": \"mysql-bin.000003\",\n" +
                "      \"pos\": 154,\n" +
                "      \"row\": 0,\n" +
                "      \"thread\": 7,\n" +
                "      \"query\": \"INSERT INTO customers (first_name, last_name, email) VALUES ('Anne', 'Kretchmar', 'annek@noanswer.org')\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        JSONObject jb= JSONObject.parseObject(jsons);
        System.out.println(jb);
        System.out.println(jb.getJSONObject("payload"));
        System.out.println(jb.getJSONObject("payload").getString("op"));
        System.out.println(jb.getJSONObject("payload").getJSONObject("source").getString("query"));
    }
}