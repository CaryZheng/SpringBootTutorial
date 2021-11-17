package com.zzb.tutorial.mahoutdemo.service.impl;

import com.zzb.tutorial.mahoutdemo.controller.TestController;
import com.zzb.tutorial.mahoutdemo.service.RecommendService;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private DataModel dataModel;

    @Override
    public List<Long> getRecommendItemsByUser(Long userId, int howMany) {
        List<Long> list = null;

        try {
            UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(10, userSimilarity, dataModel);

            Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
            List<RecommendedItem> recommendedItemList = recommender.recommend(userId, howMany);

            for(RecommendedItem item : recommendedItemList) {
                log.info("推荐结果 itemId = " + item.getItemID() + " , value = " + item.getValue());

                list.add(item.getItemID());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
