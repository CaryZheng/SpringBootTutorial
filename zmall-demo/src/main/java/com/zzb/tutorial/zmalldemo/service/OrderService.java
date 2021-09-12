package com.zzb.tutorial.zmalldemo.service;


import com.zzb.tutorial.zmalldemo.dao.GoodsOrder;

public interface OrderService {
    GoodsOrder createOrder(String orderNumber, Long userId, Long goodsId);
    GoodsOrder updateOrder(String orderNumber);
}
