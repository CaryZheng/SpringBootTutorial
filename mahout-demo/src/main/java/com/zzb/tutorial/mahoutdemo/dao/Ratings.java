package com.zzb.tutorial.mahoutdemo.dao;

import lombok.Data;

@Data
public class Ratings {
    private Long userId;
    private Long movieId;
    private String tag;
}
