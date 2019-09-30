package com.mobin.java;

public class JavaFile2 {

    public static void main(String[] args) {
        String s1= "57c16434 配音阁_iOS      7495529  ios\n" +
                "57c58b74 配音阁_android       7495529  android\n" +
                "580087ff  TTS合成助手_Android   1552241264     android\n" +
                "58008842         语音合成助手_Android  1552241264     android\n" +
                "580430fb 语音合成助手_iOS 1552241264     ios\n" +
                "5804cdc2 TTS合成助手iOS    1552241264     ios\n" +
                "580da2db         讯飞配音iOS  1552241264     ios\n" +
                "580dd3e6         讯飞配音_android  1552241264     android\n" +
                "582018d4         广告配音iOS  1552241264     ios\n" +
                "5820611b         广告配音_android  1553247489     android\n" +
                "58b38753         视频趣配音_android       1553247489     android\n" +
                "58b3e820         视频趣配音_iOS      1552241264     ios\n" +
                "58bea0e4         视频配音秀_android       1553247489     android\n" +
                "58bfce97 视频配音秀     1552241264     ios\n" +
                "58c646a9 配音软件_iOS 1552241264     ios\n" +
                "58c66a01 讯飞课件配音_android  1553247489     android\n" +
                "58c75e9f 视频配音_iOS 1552241264     ios\n" +
                "58c75ef0 配音软件_android  1553247489     android\n" +
                "58c78ff0  讯飞课件配音(专业版)_iOS   1552241264     ios\n" +
                "58c7b35f 文字转语音_iOS      1552241264     ios\n" +
                "58c8ad59 中文配音_iOS 1552241264     ios\n" +
                "58cb85db 中文配音_android  1553247489     android\n" +
                "58cf3cd4  视频配音_android  1553247489     android\n" +
                "58cf94af  促销配音_iOS 1552241264     ios\n" +
                "58cf9b20 宣传片配音_iOS      1552241264     ios\n" +
                "58d1d794         配音宝_iOS      1552241264     ios\n" +
                "58d1db7c 英文配音秀_iOS      1552241264     ios\n" +
                "58d1dc0a 声优配音_iOS 1552241264     ios\n" +
                "58d31b82         促销配音_android  1553247489     android\n" +
                "58d32fe5 宣传片配音_android       1553247489     android\n" +
                "58d47e81         文字转语音助手_android       1553247489     android\n" +
                "58d89d26         配音专业助手_android  1553247489     android\n" +
                "58d9c371 配音小咖秀_iOS      1552241264     ios\n" +
                "58d9c648 影视配音_iOS 1552241264     ios\n" +
                "58da0bd5         声优配音_android  1553247489     android\n" +
                "58da0ed2         影视配音_android  1553247489     android\n" +
                "58da10ff  英文配音秀_android       1553247489     android\n" +
                "58da1312         配音宝_android       1553247489     android\n" +
                "58da1551         配音魔方秀_ iOS     1552241264     ios\n" +
                "58da19dd         配音专业助手_iOS 1552241264     ios\n" +
                "58da1c71 方言趣配音_iOS      1552241264     ios\n" +
                "58e4d69c 广告生成神器_android  1553247489     android\n" +
                "58e5debe         配音大全_android  1553247489     android\n" +
                "58e5e055         方言趣配音_android       1553247489     android\n" +
                "58e5e5c1 配音魔方秀_android       1553247489     android\n" +
                "58e5e77e         配音小咖秀_android       1553247489     android\n" +
                "58eb3d00         变声大师_android  1553247489     android\n" +
                "58eb40e0         变声配音_android  1553247489     android\n" +
                "58eb425b         文字语音转换器_android       1553247489     android\n" +
                "58f45fd1  促销音制作_android       1553247489     android\n" +
                "58f4634a 有声课件制作_android  1553247489     android\n" +
                "58f46fcb  儿童趣配音_android       1553247489     android\n" +
                "595b7d9c 讯飞语音合成助手_Android  1553247489     android\n" +
                "595b872b         讯飞语音合成助手_iOS 1553247489     ios\n" +
                "59a51731         朗读女_android       1553247489     android\n" +
                "5a41b2dd         配音_android  1553247489     android\n" +
                "5a65b337         印度版_Android      1553247489     android\n" +
                "5ae0403b         蓝牙版_Android      1553247489     android\n" +
                "5c8b063d 广告配音助手_ios  1553247489     ios\n" +
                "5c8b0675 广告配音助手_android  1553247489     android";
        String[] sarr = s1.split("\n");
        String s3="";
        for(String s : sarr){
            System.out.println(s);
            String[] sarr2 = s.split(" ");
            System.out.println(sarr2[0]+" "+sarr2[sarr2.length-1]);
            if("android".equalsIgnoreCase(sarr2[sarr2.length-1])){
                s3+="'"+sarr2[0]+"',";
            }
        }
        System.out.println(s3);
    }
}
