package com.example.shopease.model;

public class Fashion {

    String fashion_id, fashion_imageUrl, fashion_name, fashion_price , fashion_size, fashion_rating, fashion_discription,fashion_stock;

    public Fashion(){

    }


    public Fashion(String fashion_id, String fashion_imageUrl, String fashion_name,String fashion_price , String fashion_rating, String fashion_size , String fashion_discription, String fashion_stock) {
        this.fashion_id = fashion_id;
        this.fashion_imageUrl = fashion_imageUrl;
        this.fashion_name = fashion_name;
        this.fashion_price = fashion_price;
        this.fashion_size = fashion_size;
        this.fashion_rating = fashion_rating;
        this.fashion_discription = fashion_discription;
        this.fashion_stock = fashion_stock;


    }

    public String getFashion_stock() {
        return fashion_stock;
    }

    public void setFashion_stock(String fashion_stock) {
        this.fashion_stock = fashion_stock;
    }

    public String getFashion_size() {
        return fashion_size;
    }

    public void setFashion_size(String fashion_size) {
        this.fashion_size = fashion_size;
    }

    public String getFashion_rating() {
        return fashion_rating;
    }

    public void setFashion_rating(String fashion_rating) {
        this.fashion_rating = fashion_rating;
    }

    public String getFashion_discription() {
        return fashion_discription;
    }

    public void setFashion_discription(String fashion_discription) {
        this.fashion_discription = fashion_discription;
    }

    public String getFashion_price() {
        return fashion_price;
    }

    public void setFashion_price(String fashion_price) {
        this.fashion_price = fashion_price;
    }

    public String getFashion_id() {
        return fashion_id;
    }

    public void setFashion_id(String fashion_id) {
        this.fashion_id = fashion_id;
    }

    public String getFashion_imageUrl() {
        return fashion_imageUrl;
    }

    public void setFashion_imageUrl(String fashion_imageUrl) {
        this.fashion_imageUrl = fashion_imageUrl;
    }

    public String getFashion_name() {
        return fashion_name;
    }

    public void setFashion_name(String fashion_name) {
        this.fashion_name = fashion_name;
    }
}
