package com.zzb.tutorial.webgrabdemo;

public class ResponseWrapper {

    private Integer code;
    private String msg;
    private Object obj;

    public ResponseWrapper(Object obj) {
        this.code = Message.SUCCESS.code;
        this.obj = obj;
    }

    public ResponseWrapper(Message message) {
        this.code = message.code;
        this.msg = message.message;
    }

    public ResponseWrapper(Message message, Object obj) {
        this.code = message.code;
        this.msg = message.message;
        this.obj = obj;
    }

    public static ResponseWrapper error(String message) {
        ResponseWrapper response = new ResponseWrapper(message);
        response.code = 500;
        return response;
    }

    public static ResponseWrapper badRequest() {
        ResponseWrapper response = new ResponseWrapper("不可用的请求");
        response.code = 400;
        return response;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
