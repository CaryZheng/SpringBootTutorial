package com.zzb.tutorial.jiguangdemo.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JGSmsUtil {

    // masterSecret https://www.jiguang.cn/dev2/#/overview/appCardList 开发者服务--->应用设置--->应用信息--> Master Secret
    public static String masterSecret = "a29ada0a6f3755c057942c45";

    //appKey https://www.jiguang.cn/dev2/#/overview/appCardList 开发者服务--->应用设置--->应用信息--> Master Secret
    public static String appKey = "b4bb2745d62413fce0b56bf8";

    //初始化发短信客户端
    private static SMSClient smsClient = new SMSClient(masterSecret, appKey);

    /**
     * 发送模板短信-验证码 示例1
     *
     * @param phoneNumber
     * create 2019/12/26 by kingyifan
     */
    public static void sendSMSCode(String phoneNumber, String code) {
        try {//构建发送短信
            SMSPayload payload = SMSPayload.newBuilder()
                    .setMobileNumber(phoneNumber) // 手机号码
                    .setTempId(1)            // 短信模板ID 需要自行申请 模板id为：1的则自带验证码模板id
                    .addTempPara("code", code)  // key模板参数value：参数值  您的手机验证码：{{code}}，有效期5分钟，请勿泄露。如非本人操作，请忽略此短信。谢谢！
                    .setSignId(20115)// 签名id 需要自行申请审核。个人也可以申请
                    .build();

            //发送短信 会返回msg_id
            SendSMSResult res = smsClient.sendTemplateSMS(payload);
            //////////////////////////////////////////执行业务/////////////////////////////////////////////////////
            //指向保存短信发送记录业务逻辑 可以直接扔到MQ
            /**
             * 第一个参数极光返回的消息id
             * 第二个发送的手机号
             * 第三个发送内容
             * 第四个发送时间
             * 保存到DB
             */
            //insertSendSmsLog(res.getMessageId(),phoneNumber,code,0,System.currentTimeMillis()/1000);
            //////////////////////////////////////////执行业务/////////////////////////////////////////////////////

        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * SHA1加密
     *
     * @param strSrc 明文
     * @return 加密之后的密文
     */
    public static String encrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts 数据源
     * @return 16进制字符串
     */
    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 延签判断是否是极光回调
     *
     * @param signature
     * @param nonce
     * @param timestamp
     * @return create kingyifan by  on 2019.12.26
     */
    public Boolean checkSign(String signature, String nonce, String timestamp) {
        //加密进行比对
        String str = String.format("appKey=%s&appMasterSecret=%s&nonce=%s×tamp=%s",
                appKey, masterSecret, nonce, timestamp);
        String new_signature = encrypt(str);
        if (signature.equals(new_signature)) {
            return true;
        }
        return false;
    }
}
