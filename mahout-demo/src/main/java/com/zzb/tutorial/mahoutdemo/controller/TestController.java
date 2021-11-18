package com.zzb.tutorial.mahoutdemo.controller;

import com.zzb.tutorial.mahoutdemo.service.RecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mahout")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/test1")
    public List<Long> test1() {
        List<Long> idList =  recommendService.getRecommendItemsByUser(2L, 2);

        return idList;
    }

    @GetMapping("/test2")
    public List<Long> test2() {
        List<Long> idList =  recommendService.getRecommendItemsByItem(2L, 2028L, 2);

        return idList;
    }

}
