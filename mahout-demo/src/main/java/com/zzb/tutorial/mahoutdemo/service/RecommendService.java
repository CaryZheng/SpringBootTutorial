package com.zzb.tutorial.mahoutdemo.service;

import java.util.List;

public interface RecommendService {

    // 基于用户的商品推荐
    List<Long> getRecommendItemsByUser(Long userId , int howMany);

    // 基于内容的商品推荐
    List<Long> getRecommendItemsByItem(Long userId , Long itemId , int howMany);
}
