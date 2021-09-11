package com.zzb.tutorial.zmalldemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzb.tutorial.zmalldemo.common.ResponseWrapper;
import com.zzb.tutorial.zmalldemo.dao.Goods;
import com.zzb.tutorial.zmalldemo.dao.GoodsOrder;
import com.zzb.tutorial.zmalldemo.data.OrderRD;
import com.zzb.tutorial.zmalldemo.enums.GoodsOrderState;
import com.zzb.tutorial.zmalldemo.mapper.GoodsMapper;
import com.zzb.tutorial.zmalldemo.mapper.GoodsOrderMapper;
import com.zzb.tutorial.zmalldemo.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

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

        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setOrderNumber(orderNumber);
        LambdaQueryWrapper<GoodsOrder> goodsOrderQueryWrapper = new LambdaQueryWrapper<>();
        goodsOrderQueryWrapper.eq(GoodsOrder::getOrderNumber, orderNumber);

        GoodsOrder goosdOrderResult = goodsOrderMapper.selectOne(goodsOrderQueryWrapper);
        if (null != goosdOrderResult) {
            // 订单已创建过
            return ResponseWrapper.success(goosdOrderResult);
        }

        // 创建订单
        GoodsOrder newGoodsOrder = new GoodsOrder();
        newGoodsOrder.setOrderNumber(orderNumber);
        newGoodsOrder.setUserId(userId);
        newGoodsOrder.setGoodsId(goodsId);
        newGoodsOrder.setState(GoodsOrderState.WAITTING_PAY.getValue());
        newGoodsOrder.setCreateTime(new Date());
        newGoodsOrder.setUpdateTime(new Date());

        goodsOrderMapper.insert(newGoodsOrder);
//        goodsOrderMapper.createOrder(userId, goodsId, GoodsOrderState.WAITTING_PAY.getValue());

        return ResponseWrapper.success(newGoodsOrder);
    }

    /**
     * 更新订单
     * @param orderRD
     * @return
     */
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
