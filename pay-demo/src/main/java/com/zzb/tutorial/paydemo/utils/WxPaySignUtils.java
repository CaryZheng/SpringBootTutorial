package com.zzb.tutorial.paydemo.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class WxPaySignUtils {

    public static final String MD5 = "MD5";
    public static final String HMAC_SHA256 = "HMAC-SHA256";

    /**
     * 签名
     * @param data
     * @param key
     * @param signType
     * @return
     */
    public static String sign(Map<String,String> data, String key, String signType) {
        if(null == data || data.size() == 0 || null == key || key.trim().equals("") || null == signType || !(MD5.equals(signType) || HMAC_SHA256.equals(signType))){
            throw new RuntimeException("参数缺失");
        }
        // 签名值
        String signKey = "";
        SortedMap<String, String> sortedMap = new TreeMap<>(data);
        String paramStr = "";
        for (Map.Entry<String, String> stringStringEntry : sortedMap.entrySet()) {
            if(paramStr.equals("")){
                paramStr = stringStringEntry.getKey() + "=" + stringStringEntry.getValue();
            }else{
                paramStr = paramStr + "&" +stringStringEntry.getKey() + "=" + stringStringEntry.getValue();
            }
        }
        paramStr = paramStr + "&key=" + key;
        if(signType.equals(MD5)){
            signKey = getMD5(paramStr);
        }else{
            signKey = getHmacSha256(paramStr,key);
        }
        return signKey.toUpperCase();
    }

    /**
     * 获取MD5
     * @param str
     * @return
     */
    private static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            return byteArrayToHexString(md.digest());
        } catch (Exception e) {
            throw new RuntimeException("MD5加密出现错误");
        }
    }

    /**
     * 获取HmacSha256
     * @param message
     * @param key
     * @return
     */
    private static String getHmacSha256(String message,String key){
        String outPut= null;
        try{
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(),"HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            outPut = byteArrayToHexString(bytes);
        }catch (Exception e){
            throw new RuntimeException("HmacSHA256加密出现错误");
        }
        return outPut;
    }

    /**
     * byte数组转16进制字符串
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                sb.append('0');
            sb.append(stmp);
        }
        return sb.toString().toLowerCase();
    }
}

