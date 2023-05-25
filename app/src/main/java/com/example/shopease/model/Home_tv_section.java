package com.example.shopease.model;

public class Home_tv_section {

    String tv_id,tv_name,imageUrl;

    public Home_tv_section(){

    }


    public Home_tv_section(String category_id, String imageUrl, String category_name) {
        this.tv_id = category_id;
        this.imageUrl = imageUrl;
        this.tv_name = category_name;

    }

    public String getTv_id() {
        return tv_id;
    }

    public void setTv_id(String tv_id) {
        this.tv_id = tv_id;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
