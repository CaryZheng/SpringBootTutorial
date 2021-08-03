package com.zzb.tutorial.datajpademo.controller;

import com.zzb.tutorial.datajpademo.dao.GoodsOrderDao;
import com.zzb.tutorial.datajpademo.entity.GoodsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test/order")
public class GoodsOrderController {

    @Autowired
    private GoodsOrderDao goodsOrderDao;

    @GetMapping("/all")
    public List<GoodsOrder> findAllGoodsOrder() {
        List<GoodsOrder> result = goodsOrderDao.findAll();

        return result;
    }
}
