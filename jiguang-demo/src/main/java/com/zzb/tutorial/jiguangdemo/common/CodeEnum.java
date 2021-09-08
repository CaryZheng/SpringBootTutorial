package com.zzb.tutorial.jiguangdemo.common;

public enum CodeEnum {

    /** -- 0开头系统级别响应码 --**/
    SUCCESS("000000","Successful"),

    CODE_100001("100001","参数错误"),

    CODE_200001("200001","Jiguang verify fail");

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
