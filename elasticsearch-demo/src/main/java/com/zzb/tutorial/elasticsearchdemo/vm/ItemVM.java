package com.zzb.tutorial.elasticsearchdemo.vm;

import lombok.Data;

import java.util.Date;

@Data
public class ItemVM {
    private Long id;
    private String postTitle;
    private String postText;
    private String[] postTag;
    private Integer price;
    private Date publishTime;
}
