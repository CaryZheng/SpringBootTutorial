package com.zzb.tutorial.zmalldemo.enums;

public enum GoodsOrderState {

    WAITTING_PAY(1),        // 待支付
    FINISH_PAY(2),          // 已支付
    CLOSED(3);              // 已关闭

    private int value;

    GoodsOrderState(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
