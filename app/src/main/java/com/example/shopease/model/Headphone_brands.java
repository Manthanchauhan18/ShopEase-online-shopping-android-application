package com.example.shopease.model;

public class Headphone_brands {

    String headphone_brand_id, headphone_brand_imageUrl, headphone_brand_name;

    public Headphone_brands(){

    }

    public Headphone_brands(String headphone_brand_id, String hedphone_brand_imageUrl, String headphone_brand_name){
        this.headphone_brand_id = headphone_brand_id;
        this.headphone_brand_imageUrl = hedphone_brand_imageUrl;
        this.headphone_brand_name = headphone_brand_name;
    }

    public String getHeadphone_brand_id() {
        return headphone_brand_id;
    }

    public void setHeadphone_brand_id(String headphone_brand_id) {
        this.headphone_brand_id = headphone_brand_id;
    }

    public String getHeadphone_brand_imageUrl() {
        return headphone_brand_imageUrl;
    }

    public void setHeadphone_brand_imageUrl(String headphone_brand_imageUrl) {
        this.headphone_brand_imageUrl = headphone_brand_imageUrl;
    }

    public String getHeadphone_brand_name() {
        return headphone_brand_name;
    }

    public void setHeadphone_brand_name(String headphone_brand_name) {
        this.headphone_brand_name = headphone_brand_name;
    }
}
