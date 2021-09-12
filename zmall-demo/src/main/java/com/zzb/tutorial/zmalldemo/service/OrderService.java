package com.zzb.tutorial.zmalldemo.service;


import com.zzb.tutorial.zmalldemo.dao.GoodsOrder;
import com.zzb.tutorial.zmalldemo.dto.GoodsOrderDTO;

public interface OrderService {
    GoodsOrderDTO createOrder(String orderNumber, Long userId, Long goodsId);
    GoodsOrder updateOrder(String orderNumber);
}
