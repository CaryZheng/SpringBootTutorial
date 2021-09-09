package com.zzb.tutorial.zmalldemo.data;

import lombok.Data;

@Data
public class OrderRD {
    private Long id;
    private Long userId;
    private Long goodsId;
    private int state;
}
