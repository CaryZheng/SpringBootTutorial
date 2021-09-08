package com.zzb.tutorial.jiguangdemo.common;

public class ResponseWrapper<T> {
    private String code;
    private String msg;
    private T data;

    public ResponseWrapper() {}

    public ResponseWrapper(T data) {
        this.code = CodeEnum.SUCCESS.getCode();
        this.data = data;
    }

    public ResponseWrapper(String code, String message) {
        this.code = code;
        this.msg = message;
        this.data = null;
    }

    public ResponseWrapper(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = null;
    }

    public ResponseWrapper(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResponseWrapper<?> success() {
        ResponseWrapper<?> response = new ResponseWrapper<>(CodeEnum.SUCCESS);
        return response;
    }

    public static <T> ResponseWrapper<T> success(T obj) {
        ResponseWrapper<T> response = new ResponseWrapper<>(CodeEnum.SUCCESS, obj);
        return response;
    }

    public static ResponseWrapper<?> fail(CodeEnum message) {
        ResponseWrapper<?> response = new ResponseWrapper<>(message);
        return response;
    }

}
