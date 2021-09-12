package com.zzb.tutorial.zmalldemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzb.tutorial.zmalldemo.dao.Goods;
import com.zzb.tutorial.zmalldemo.dao.GoodsOrder;
import com.zzb.tutorial.zmalldemo.dto.GoodsOrderDTO;
import com.zzb.tutorial.zmalldemo.enums.GoodsOrderState;
import com.zzb.tutorial.zmalldemo.exception.OrderException;
import com.zzb.tutorial.zmalldemo.mapper.GoodsMapper;
import com.zzb.tutorial.zmalldemo.mapper.GoodsOrderMapper;
import com.zzb.tutorial.zmalldemo.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public GoodsOrderDTO createOrder(String orderNumber, Long userId, Long goodsId) {
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setOrderNumber(orderNumber);
        LambdaQueryWrapper<GoodsOrder> goodsOrderQueryWrapper = new LambdaQueryWrapper<>();
        goodsOrderQueryWrapper.eq(GoodsOrder::getOrderNumber, orderNumber);

        GoodsOrder goosdOrderResult = goodsOrderMapper.selectOne(goodsOrderQueryWrapper);
        if (null != goosdOrderResult) {
            // 订单已创建过
            GoodsOrderDTO goodsOrderDTO = new GoodsOrderDTO();
            BeanUtils.copyProperties(goosdOrderResult, goodsOrderDTO);

            return goodsOrderDTO;
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

        GoodsOrderDTO goodsOrderDTO = new GoodsOrderDTO();
        BeanUtils.copyProperties(newGoodsOrder, goodsOrderDTO);

        // TODO: 目前 totalPrice 为测试数据
        goodsOrderDTO.setTotalPrice(BigDecimal.valueOf(99.9));

        return goodsOrderDTO;
    }

    @Transactional
    @Override
    public GoodsOrder updateOrder(String orderNumber) {
        // 查询订单
        GoodsOrder goodsOrder = goodsOrderMapper.selectByOrderNumberForUpdate(orderNumber);
        int state = goodsOrder.getState();
        if (state == GoodsOrderState.WAITTING_PAY.getValue()) {
            Long goodsId = goodsOrder.getGoodsId();

            // 查询商品数量
            Goods goods = goodsMapper.selectByIdForUpdate(goodsId);
            if (goods.getNum() < 1) {
//                return ResponseWrapper.fail(CodeEnum.CODE_300001);
                throw new OrderException("订单商品数量不足");
            }
            goods.setNum(goods.getNum()-1);

            // 更新商品数量
            goodsMapper.updateById(goods);

            // 更新订单状态
            goodsOrder.setState(GoodsOrderState.FINISH_PAY.getValue());
            goodsOrderMapper.updateById(goodsOrder);
        }

        return goodsOrder;
    }
}
