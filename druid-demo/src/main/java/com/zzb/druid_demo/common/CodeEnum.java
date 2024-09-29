package com.zzb.druid_demo.common;

public enum CodeEnum {

    /** -- 0开头系统级别响应码 --**/
    SUCCESS("000000","Successful"),

    CODE_000001("000001","System handle fail"),
    CODE_000002("000002","Token is invalid"),

    /** -- 1为公共业务通用错误响应码 --**/
    CODE_100002("100002","参数错误"),

    /** -- 2为用户相关错误响应码 --**/
    CODE_200001("200001","该账号已存在"),
    CODE_200002("200002","账号或密码错误"),
    CODE_200003("200003","该账号不存在"),
    CODE_200004("200004","该好友申请不存在"),
    CODE_200005("200005","非好友关系"),

    /** -- 3为订单相关错误响应码 --**/
    CODE_300001("300001","订单商品数量不足");

    private String code;
    private String msg;

    CodeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
