package com.example.shopease.model;

public class Appliance {

    String appliance_id, imageUrl, appliance_name , appliance_price , appliance_weight , appliance_rating , appliance_discription, appliamce_stock;

    public Appliance(){

    }


    public Appliance(String appliance_id, String imageUrl, String appliance_name,String appliance_price,String appliance_weight,String appliance_rating,String appliance_discription,String appliance_stock) {
        this.appliance_id = appliance_id;
        this.imageUrl = imageUrl;
        this.appliance_name = appliance_name;
        this.appliance_price = appliance_price;
        this.appliance_weight = appliance_weight;
        this.appliance_rating = appliance_rating;
        this.appliance_discription = appliance_discription;
        this.appliamce_stock = appliance_stock;

    }

    public String getAppliamce_stock() {
        return appliamce_stock;
    }

    public void setAppliamce_stock(String appliamce_stock) {
        this.appliamce_stock = appliamce_stock;
    }

    public String getAppliance_weight() {
        return appliance_weight;
    }

    public void setAppliance_weight(String appliance_weight) {
        this.appliance_weight = appliance_weight;
    }

    public String getAppliance_rating() {
        return appliance_rating;
    }

    public void setAppliance_rating(String appliance_rating) {
        this.appliance_rating = appliance_rating;
    }

    public String getAppliance_discription() {
        return appliance_discription;
    }

    public void setAppliance_discription(String appliance_discription) {
        this.appliance_discription = appliance_discription;
    }

    public String getAppliance_id() {
        return appliance_id;
    }

    public void setAppliance_id(String appliance_id) {
        this.appliance_id = appliance_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAppliance_name() {
        return appliance_name;
    }

    public void setAppliance_name(String appliance_name) {
        this.appliance_name = appliance_name;
    }

    public String getAppliance_price() {
        return appliance_price;
    }

    public void setAppliance_price(String appliance_price) {
        this.appliance_price = appliance_price;
    }
}
