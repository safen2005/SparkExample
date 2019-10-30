package com.mobin.java;

public class JavaTest3 {

    public static void setValue(String s){
        s = "新";
        System.out.println("1:"+s);
    }
    public static void main(String[] args) {
//        String s = "旧";
//        setValue(s);
//        System.out.println("2:"+s);
        int num = 10;
        int singleNum = 10;
        int n = 1;
        if(num>=singleNum){
            n = (num/singleNum);
            if(num%singleNum!=0){
                n=n+1;
            }
        }
        System.out.println(n);
    }
}