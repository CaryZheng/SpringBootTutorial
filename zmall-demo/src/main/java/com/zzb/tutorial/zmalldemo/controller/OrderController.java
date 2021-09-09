package com.zzb.tutorial.zmalldemo.controller;

import com.zzb.tutorial.zmalldemo.common.ResponseWrapper;
import com.zzb.tutorial.zmalldemo.dao.Goods;
import com.zzb.tutorial.zmalldemo.dao.GoodsOrder;
import com.zzb.tutorial.zmalldemo.data.OrderRD;
import com.zzb.tutorial.zmalldemo.enums.GoodsOrderState;
import com.zzb.tutorial.zmalldemo.mapper.GoodsMapper;
import com.zzb.tutorial.zmalldemo.mapper.GoodsOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @PostMapping("/create")
    public ResponseWrapper createOrder(@RequestBody OrderRD orderRD) {
        Long userId = orderRD.getUserId();
        Long goodsId = orderRD.getGoodsId();

        goodsOrderMapper.createOrder(userId, goodsId, GoodsOrderState.WAITTING_PAY.getValue());

        return ResponseWrapper.success();
    }

    @PostMapping("/update")
    @Transactional
    public ResponseWrapper updateOrderState(@RequestBody OrderRD orderRD) {
        Long orderId = orderRD.getId();

        GoodsOrder goodsOrder = goodsOrderMapper.selectByIdForUpdate(orderId);
        int state = goodsOrder.getState();
        if (state == GoodsOrderState.WAITTING_PAY.getValue()) {
            goodsOrder.setState(GoodsOrderState.FINISH_PAY.getValue());

            Long goodsId = goodsOrder.getGoodsId();
            Goods goods = goodsMapper.selectByIdForUpdate(goodsId);
            goods.setNum(goods.getNum()-1);
            goodsMapper.updateById(goods);

            goodsOrderMapper.updateById(goodsOrder);
        }

        return ResponseWrapper.success(goodsOrder);
    }
}
