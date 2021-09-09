package com.zzb.tutorial.paydemo.controller;

import com.zzb.tutorial.paydemo.utils.WxPaySignUtils;
import com.zzb.tutorial.paydemo.wxpay.sdk.WXPay;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static final String appid = "";
    private static final String mch_id = "";
    private static final String key = "";

    private WXPay wxPay;

    @GetMapping("/createOrder")
    public String createWechatOrder() throws Exception {
        Map<String, String> reqData = new HashMap<>();

        // 随机字符串
        String nonce_str = "5K8264ILTKCH16CQ2502SI8ZNMTM67VS";
        String body = "";           // 商品描述
        String out_trade_no = "";   // 商户订单号
        int total_fee = 99;         // 总金额
        String spbill_create_ip = "123.12.12.123";
        String notify_url = "";     // 通知地址
        String trade_type = "APP";  // 交易类型

        // 填充参数
        reqData.put("appid", appid);
        reqData.put("mch_id", mch_id);
        reqData.put("nonce_str", nonce_str);
        reqData.put("body", body);
        reqData.put("total_fee", String.valueOf(total_fee));
        reqData.put("notify_url", notify_url);

        String sign = WxPaySignUtils.sign(reqData, key, WxPaySignUtils.MD5);

        reqData.put("sign", sign);

        Map<String, String> result = wxPay.unifiedOrder(reqData);

        // 需返回 应用ID、商户号、预支付交易会话ID、扩展字段、随机字符串、时间戳、签名 给app端

        return "createWechatOrder";
    }

    // 异步回调支付结果
    @GetMapping("/notifyResult")
    public String notifyWechatResult() {
        return "notifyWechatResult";
    }
}
