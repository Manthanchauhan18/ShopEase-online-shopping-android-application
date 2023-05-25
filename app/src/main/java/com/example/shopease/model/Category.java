package com.example.shopease.model;

public class Category {

    String category_id, imageUrl, category_name;

    public Category(){

    }


    public Category(String category_id, String imageUrl, String category_name) {
        this.category_id = category_id;
        this.imageUrl = imageUrl;
        this.category_name = category_name;

    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
