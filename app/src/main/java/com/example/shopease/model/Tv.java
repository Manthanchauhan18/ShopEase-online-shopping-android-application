package com.example.shopease.model;

public class Tv {

    String tv_id , imageUrl_tv, tv_name , tv_price, tv_size , tv_rating , tv_discription ,tv_stock;

    public Tv(){

    }

    public Tv(String tv_id , String imageUrl_tv, String tv_name , String tv_price, String tv_size , String tv_rating , String tv_discription , String tv_stock){

        this.tv_id = tv_id;
        this.imageUrl_tv = imageUrl_tv;
        this.tv_name = tv_name;
        this.tv_size = tv_size;
        this.tv_rating = tv_rating;
        this.tv_price = tv_price;
        this.tv_discription = tv_discription;
        this.tv_stock = tv_stock;

    }

    public String getTv_id() {
        return tv_id;
    }

    public void setTv_id(String tv_id) {
        this.tv_id = tv_id;
    }

    public String getImageUrl_tv() {
        return imageUrl_tv;
    }

    public void setImageUrl_tv(String imageUrl_tv) {
        this.imageUrl_tv = imageUrl_tv;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_price() {
        return tv_price;
    }

    public void setTv_price(String tv_price) {
        this.tv_price = tv_price;
    }

    public String getTv_size() {
        return tv_size;
    }

    public void setTv_size(String tv_size) {
        this.tv_size = tv_size;
    }

    public String getTv_rating() {
        return tv_rating;
    }

    public void setTv_rating(String tv_rating) {
        this.tv_rating = tv_rating;
    }


    public String getTv_discription() {
        return tv_discription;
    }

    public void setTv_discription(String tv_discription) {
        this.tv_discription = tv_discription;
    }

    public String getTv_stock() {
        return tv_stock;
    }

    public void setTv_stock(String tv_stock) {
        this.tv_stock = tv_stock;
    }
}
