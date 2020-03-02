package com.mobin.java;

public class JavaFile {

    public static void main(String[] args) {
        System.out.println("ods_cbg_iflyrec_transcriptengineproxy_AudioTranscriptTasks".toLowerCase());

        String s1= "Id\n" +
                "BizId\n" +
                "TraceId\n" +
                "TaskId\n" +
                "Language\n" +
                "HotWords\n" +
                "PatchIds\n" +
                "AudioName\n" +
                "AudioInfo\n" +
                "AudioFileSize\n" +
                "CallbackUrl\n" +
                "AudioFileUrl\n" +
                "ScheduleInfo\n" +
                "Priority\n" +
                "Status\n" +
                "FailDescription\n" +
                "CallbackStatus\n" +
                "CallbackCount\n" +
                "ChannelId\n" +
                "TransRequestSendTime\n" +
                "ReceiveEngineTime\n" +
                "CallbackTime\n" +
                "CreateTime\n" +
                "UpdateTime\n" +
                "IsIdleOrder\n" +
                "AudioId";
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
