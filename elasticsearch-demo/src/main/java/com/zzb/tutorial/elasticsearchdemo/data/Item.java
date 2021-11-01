package com.zzb.tutorial.elasticsearchdemo.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "my_post")
public class Item {
    @Id
    private Long id;

    @Field(name = "post_text", type = FieldType.Text)
    private String postText;

    @Field(name = "post_tag", type = FieldType.Text)
    private String postTag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostTag() {
        return postTag;
    }

    public void setPostTag(String postTag) {
        this.postTag = postTag;
    }
}
