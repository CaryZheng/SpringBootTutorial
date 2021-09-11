package com.zzb.tutorial.zmalldemo.common;

public enum CodeEnum {

    /** -- 0开头系统级别响应码 --**/
    SUCCESS("000000","Successful"),

    CODE_100001("100001","Token无效"),
    CODE_100002("100002","参数错误"),

    /** -- 用户相关 --**/
    CODE_200001("200001","该账号已存在"),
    CODE_200002("200002","账号或密码错误"),
    CODE_200003("200003","该账号不存在");

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
