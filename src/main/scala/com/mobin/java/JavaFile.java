package com.mobin.java;

public class JavaFile {

    public static void main(String[] args) {
        System.out.println("ods_cbg_iflyrec_l1_t_meeting_attendee".toLowerCase());

        String s1= "ma_id\n" +
                "m_id\n" +
                "join_time\n" +
                "leave_time\n" +
                "ds_id\n" +
                "device_type\n" +
                "status\n" +
                "created_time\n" +
                "created_by\n" +
                "updated_time\n" +
                "updated_by";
        String[] sarr = s1.split("\n");
        String s3="";
        String s4="";
        String s5="";
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
            s5+="concat("+s.toLowerCase()+",'')as "+s2+",";
        }
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
//        s4="";
//        for(String s : sarr){
//            s4+=s.toLowerCase()+",";
//        }
//        System.out.println(s4);
    }
}
