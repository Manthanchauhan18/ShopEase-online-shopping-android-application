package com.example.shopease.model;

public class Sports {

    String sports_id, sports_imageUrl, sports_name, sports_price,sports_rating,sports_discription,sports_stock;

    public Sports(){

    }

    public Sports(String sports_id, String sports_imageUrl, String sports_name ,  String sports_price,String sports_rating,String sports_discription,String sports_stock){
        this.sports_id = sports_id;
        this.sports_imageUrl = sports_imageUrl;
        this.sports_name = sports_name;
        this.sports_price = sports_price;
        this.sports_rating = sports_rating;
        this.sports_discription = sports_discription;
        this.sports_stock = sports_stock;

    }

    public String getSports_stock() {
        return sports_stock;
    }

    public void setSports_stock(String sports_stock) {
        this.sports_stock = sports_stock;
    }

    public String getSports_rating() {
        return sports_rating;
    }

    public void setSports_rating(String sports_rating) {
        this.sports_rating = sports_rating;
    }

    public String getSports_discription() {
        return sports_discription;
    }

    public void setSports_discription(String sports_discription) {
        this.sports_discription = sports_discription;
    }

    public String getSports_id() {
        return sports_id;
    }

    public void setSports_id(String sports_id) {
        this.sports_id = sports_id;
    }

    public String getSports_imageUrl() {
        return sports_imageUrl;
    }

    public void setSports_imageUrl(String sports_imageUrl) {
        this.sports_imageUrl = sports_imageUrl;
    }

    public String getSports_name() {
        return sports_name;
    }

    public void setSports_name(String sports_name) {
        this.sports_name = sports_name;
    }

    public String getSports_price() {
        return sports_price;
    }

    public void setSports_price(String sports_price) {
        this.sports_price = sports_price;
    }


}
