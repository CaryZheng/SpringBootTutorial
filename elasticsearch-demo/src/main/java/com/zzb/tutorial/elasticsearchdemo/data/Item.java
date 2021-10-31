package com.zzb.tutorial.elasticsearchdemo.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "my_post")
public class Item {
    @Id
    private Long post_id;

    @Field(type = FieldType.Text)
    private String post_text;

    @Field(type = FieldType.Text)
    private String post_tag;

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public String getPost_tag() {
        return post_tag;
    }

    public void setPost_tag(String post_tag) {
        this.post_tag = post_tag;
    }
}
