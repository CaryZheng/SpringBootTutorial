package com.zzb.tutorial.datajpademo.dao;

import com.zzb.tutorial.datajpademo.entity.GoodsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsOrderDao extends JpaRepository<GoodsOrder, Integer> {

}
