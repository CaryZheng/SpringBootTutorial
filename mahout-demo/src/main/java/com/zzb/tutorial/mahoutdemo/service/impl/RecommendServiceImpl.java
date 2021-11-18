package com.zzb.tutorial.mahoutdemo.service.impl;

import com.zzb.tutorial.mahoutdemo.controller.TestController;
import com.zzb.tutorial.mahoutdemo.service.RecommendService;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private DataModel dataModel;

    @Override
    public List<Long> getRecommendItemsByUser(Long userId, int howMany) {
        List<Long> list = new ArrayList<>();

        try {
            UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(3, userSimilarity, dataModel);

            Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
            List<RecommendedItem> recommendedItemList = recommender.recommend(userId, howMany);

            if (null == recommendedItemList) {
                log.info("基于用户的推荐结果 recommendedItemList = null");
            } else {
                log.info("基于用户的推荐结果 recommendedItemList size = " + recommendedItemList.size());
            }

            for(RecommendedItem item : recommendedItemList) {

                long itemId = item.getItemID();
                float value = item.getValue();
                log.info("基于用户的推荐结果 itemId = " + itemId + " , value = " + value);

                list.add(itemId);
            }

        } catch (Exception e) {
            log.info("基于用户的推荐结果 exception = " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Long> getRecommendItemsByItem(Long userId, Long itemId, int howMany) {
        List <Long> list = new ArrayList<>();

        try {
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
            GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
            List<RecommendedItem> recommendedItemList = recommender.recommendedBecause(userId, itemId, howMany);

            if (null == recommendedItemList) {
                log.info("基于内容的推荐结果 recommendedItemList = null");
            } else {
                log.info("基于内容的推荐结果 recommendedItemList size = " + recommendedItemList.size());
            }

            for (RecommendedItem item : recommendedItemList) {
                log.info("基于内容的推荐结果 itemId = " + item.getItemID() + " , value = " + item.getValue());

                list.add(item.getItemID());
            }
        } catch (Exception e) {
            log.info("基于内容的推荐结果 exception = " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }
}
