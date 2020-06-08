package com.gy.Utill;

import java.security.MessageDigest;

public class MD5 {
    public String md5Change(String pwd){
        MessageDigest digest = null;
        try{
            digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(pwd.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b:result){
                int number = b & 0xff;
                String hex = Integer.toHexString(	number);
                if(hex.length()==1){
                    sb.append("0"+hex);
                }else{
                    sb.append(hex);
                }

            }
            return sb.toString();
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
		/*for(int i=0;i<result.length;i++){
			byte b = result[i];
		}*/
    }
}
