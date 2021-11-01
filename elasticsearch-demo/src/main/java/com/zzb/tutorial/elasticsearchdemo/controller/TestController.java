package com.zzb.tutorial.elasticsearchdemo.controller;

import com.zzb.tutorial.elasticsearchdemo.data.Item;
import com.zzb.tutorial.elasticsearchdemo.vm.ItemVM;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/es")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 创建索引
     * @return
     */
    @PostMapping("/index/create")
    public boolean createIndex() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Item.class);
        boolean result = indexOperations.create();

        return result;
    }

    /**
     * 删除索引
     * @return
     */
    @PostMapping("/index/delete")
    public boolean deleteIndex() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Item.class);
        boolean result = indexOperations.delete();

        return result;
    }

    /**
     * 添加文档
     * @param itemVM
     * @return
     */
    @PostMapping("/doc/create")
    public String insertDoc(@RequestBody ItemVM itemVM) {
        Item item = new Item();
        item.setId(itemVM.getId());
        item.setPostText(itemVM.getPostText());
        item.setPostTag(itemVM.getPostTag());

        elasticsearchRestTemplate.save(item);

        return "success";
    }

    /**
     * 查找全部文档
     * @return
     */
    @GetMapping("/doc/search/all")
    public List<SearchHit<Item>> docSearchAll() {
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        SearchHits<Item> hits = elasticsearchRestTemplate.search(searchQueryBuilder.build(), Item.class);

        List<SearchHit<Item>> searchHitList =  hits.getSearchHits();
        searchHitList.forEach(hit -> {
            Long id = hit.getContent().getId();
            String text = hit.getContent().getPostText();
            String tag = hit.getContent().getPostTag();
            log.info("返回数据: id = " + id + " , text = " + text + " , tag = " + tag);
        });

        return searchHitList;
    }

    /**
     * 根据关键词查找文档
     * @param queryText
     * @return
     */
    @GetMapping("/doc/search")
    public List<SearchHit<Item>> docSearchWithParam(@RequestParam String queryText) {
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        searchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(queryText).field("post_text"));

        SearchHits<Item> hits = elasticsearchRestTemplate.search(searchQueryBuilder.build(), Item.class);

        List<SearchHit<Item>> searchHitList =  hits.getSearchHits();
        searchHitList.forEach(hit -> {
            Long id = hit.getContent().getId();
            String text = hit.getContent().getPostText();
            String tag = hit.getContent().getPostTag();
            log.info("返回数据: id = " + id + " , text = " + text + " , tag = " + tag);
        });

        return searchHitList;
    }
}
