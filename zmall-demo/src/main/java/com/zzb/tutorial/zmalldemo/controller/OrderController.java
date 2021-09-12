package com.zzb.tutorial.zmalldemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzb.tutorial.zmalldemo.common.ResponseWrapper;
import com.zzb.tutorial.zmalldemo.dao.GoodsOrder;
import com.zzb.tutorial.zmalldemo.data.OrderRD;
import com.zzb.tutorial.zmalldemo.service.OrderService;
import com.zzb.tutorial.zmalldemo.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单号（目的是为了防止重复下单）
     * @return
     */
    @PostMapping("/orderNumber")
    public ResponseWrapper createOrderNumber() {
        String uuid = UuidUtils.getUUID();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNumber", uuid);

        return ResponseWrapper.success(jsonObject);
    }

    /**
     * 创建订单
     * @param orderRD
     * @return
     */
    @PostMapping("/create")
    public ResponseWrapper createOrder(@RequestBody OrderRD orderRD) {
        String orderNumber = orderRD.getOrderNumber();
        Long userId = orderRD.getUserId();
        Long goodsId = orderRD.getGoodsId();

        GoodsOrder newGoodsOrder = orderService.createOrder(orderNumber, userId, goodsId);

        return ResponseWrapper.success(newGoodsOrder);
    }

    /**
     * 支付宝回调结果
     * @param orderRD
     * @return
     */
    @PostMapping("/alipayNotify")
    public ResponseWrapper onAlipayNotify(@RequestBody OrderRD orderRD) {
        String orderNumber = orderRD.getOrderNumber();

        GoodsOrder goodsOrder = orderService.updateOrder(orderNumber);

        return ResponseWrapper.success(goodsOrder);
    }
}
