package com.zzb.tutorial.zmalldemo.controller;

import com.zzb.tutorial.zmalldemo.common.ResponseWrapper;
import com.zzb.tutorial.zmalldemo.dao.GoodsOrder;
import com.zzb.tutorial.zmalldemo.data.OrderRD;
import com.zzb.tutorial.zmalldemo.enums.GoodsOrderState;
import com.zzb.tutorial.zmalldemo.mapper.GoodsOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @PostMapping("/create")
    public ResponseWrapper createOrder(@RequestBody OrderRD orderRD) {
        Long userId = orderRD.getUserId();
        Long goodsId = orderRD.getGoodsId();

        goodsOrderMapper.createOrder(userId, goodsId, GoodsOrderState.WAITTING_PAY.getValue());

        return ResponseWrapper.success();
    }

    @PostMapping("/update")
    public ResponseWrapper updateOrderState(@RequestBody OrderRD orderRD) {
        Long orderId = orderRD.getId();
        Long userId = orderRD.getUserId();
        Long goodsId = orderRD.getGoodsId();
        int state = orderRD.getState();

        GoodsOrder goodsOrder = goodsOrderMapper.selectById(orderId);

        return ResponseWrapper.success(goodsOrder);
    }
}
