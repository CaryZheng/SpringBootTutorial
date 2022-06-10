package com.zzb.tutorial.webgrabdemo;

public enum Message {

    SUCCESS(200, "成功"),

    FAIL(100, "失败");

    public int code;
    public String message;

    Message(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
