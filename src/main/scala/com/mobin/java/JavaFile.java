package com.mobin.java;

public class JavaFile {

    public static void main(String[] args) {
        System.out.println("ods_cbg_iflyrec_order_PmCoupon".toLowerCase());

        String s1= "Id\n" +
                "CouponCode\n" +
                "Name\n" +
                "CouponDesc\n" +
                "PAId\n" +
                "Description\n" +
                "UserId\n" +
                "Status\n" +
                "ValidBeginTime\n" +
                "ExpireTime\n" +
                "CreatorType\n" +
                "CreatorId\n" +
                "CouponType\n" +
                "PromotionEntityInfo\n" +
                "UsedTime\n" +
                "CreateTime\n" +
                "UpdateTime";
        String[] sarr = s1.split("\n");
        //System.out.println(sarr.length);
        String s3="";
        //concat(Id,'')as id
        String s4="";
        for(String s : sarr){
            char[] carr= s.toCharArray();
            String s2 = (carr[0]+"").toLowerCase();
            for(int i=1;i<carr.length;i++){
                //是否大写
                if(Character.isUpperCase(carr[i])){
                    s2+=("_"+carr[i]).toLowerCase();
                }else {
                    s2+=carr[i]+"";
                }
            }
            s3+=s2+" STRING COMMENT '',\n";
            s4+="concat("+s+",'')as "+s2+",";
        }
        System.out.println(s3);
        System.out.println(s4);
        s4="";
        for(String s : sarr){
            s4+=s+",";
        }
        System.out.println(s4);
    }
}
