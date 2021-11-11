package com.zzb.tutorial.elasticsearchdemo.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "my_post")
public class Item {
    @Id
    private Long id;

    @Field(name = "post_title", type = FieldType.Keyword)
    private String postTitle;

    @Field(name = "post_text", type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String postText;

    @Field(name = "post_tag", type = FieldType.Keyword)
    private String[] postTag;

    @Field(name = "price", type = FieldType.Integer)
    private Integer price;

    @Field(name = "publish_time", type = FieldType.Date)
    private Date publishTime;
}
