package com.zzb.tutorial.elasticsearchdemo.vm;

public class ItemVM {
    private Long id;
    private String postText;
    private String[] postTag;

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

    public String[] getPostTag() {
        return postTag;
    }

    public void setPostTag(String[] postTag) {
        this.postTag = postTag;
    }
}
