package com.zzb.tutorial.zmalldemo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsOrderDTO {

    private Long id;
    private String orderNumber;
    private Long userId;
    private Long goodsId;
    private Integer state;
    private Date createTime;
    private Date updateTime;
    private BigDecimal totalPrice;
}
