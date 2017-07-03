package com.wuzhichao.test1.mvp.bean;

/**
 * Created by 黑客 on 2017/6/30.
 */

public class BarInfoBean {
    private int follow_count;
    private int post_count;
    private String description;
    private int id;
    private String name;
    private String image;
    private boolean isCare;
    private int exp;
    private String postBarStartTime;
    private String postBarModifyTime;

    public int getFollow_count() {
        return follow_count;
    }

    public void setFollow_count(int follow_count) {
        this.follow_count = follow_count;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isCare() {
        return isCare;
    }

    public void setCare(boolean care) {
        isCare = care;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getPostBarStartTime() {
        return postBarStartTime;
    }

    public void setPostBarStartTime(String postBarStartTime) {
        this.postBarStartTime = postBarStartTime;
    }

    public String getPostBarModifyTime() {
        return postBarModifyTime;
    }

    public void setPostBarModifyTime(String postBarModifyTime) {
        this.postBarModifyTime = postBarModifyTime;
    }
    //private List<PostDto> listPost;
}
