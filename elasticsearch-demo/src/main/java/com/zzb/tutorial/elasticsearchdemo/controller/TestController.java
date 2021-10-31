package com.zzb.tutorial.elasticsearchdemo.controller;

import com.zzb.tutorial.elasticsearchdemo.data.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/es")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @GetMapping("/doc/create")
    public String docCreate() {
        Item item = new Item();
        item.setPost_id(2L);
        item.setPost_text("test from zzb 2");
        item.setPost_tag("测试2");

        try {
            elasticsearchRestTemplate.save(item);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    @GetMapping("/doc/search")
    public String docSearch() {
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        SearchHits<Item> hits = elasticsearchRestTemplate.search(searchQueryBuilder.build(), Item.class);

        List<SearchHit<Item>> searchHitList =  hits.getSearchHits();
        searchHitList.forEach(hit -> {
            Long id = hit.getContent().getPost_id();
            String text = hit.getContent().getPost_text();
            String tag = hit.getContent().getPost_tag();
            log.info("返回数据: id = " + id + " , text = " + text + " , tag = " + tag);
        });

        return "success";
    }
}
