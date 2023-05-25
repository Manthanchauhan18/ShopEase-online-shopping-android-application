package com.example.shopease.model;

public class Furniture {

    String furniture_id, furniture_imageUrl, furniture_name, furniture_price , furniture_rating, furniture_discription,furniture_stock;

    public Furniture(){

    }

    public Furniture(String furniture_id, String furniture_imageUrl, String furniture_name,String furniture_price , String furniture_rating, String furniture_discription , String furniture_stock) {
        this.furniture_id = furniture_id;
        this.furniture_imageUrl = furniture_imageUrl;
        this.furniture_name = furniture_name;
        this.furniture_price = furniture_price;
        this.furniture_rating = furniture_rating;
        this.furniture_discription = furniture_discription;
        this.furniture_stock = furniture_stock;

    }

    public String getFurniture_stock() {
        return furniture_stock;
    }

    public void setFurniture_stock(String furniture_stock) {
        this.furniture_stock = furniture_stock;
    }

    public String getFurniture_rating() {
        return furniture_rating;
    }

    public void setFurniture_rating(String furniture_rating) {
        this.furniture_rating = furniture_rating;
    }

    public String getFurniture_discription() {
        return furniture_discription;
    }

    public void setFurniture_discription(String furniture_discription) {
        this.furniture_discription = furniture_discription;
    }

    public String getFurniture_id() {
        return furniture_id;
    }

    public void setFurniture_id(String furniture_id) {
        this.furniture_id = furniture_id;
    }

    public String getFurniture_imageUrl() {
        return furniture_imageUrl;
    }

    public void setFurniture_imageUrl(String furniture_imageUrl) {
        this.furniture_imageUrl = furniture_imageUrl;
    }

    public String getFurniture_name() {
        return furniture_name;
    }

    public void setFurniture_name(String furniture_name) {
        this.furniture_name = furniture_name;
    }

    public String getFurniture_price() {
        return furniture_price;
    }

    public void setFurniture_price(String furniture_price) {
        this.furniture_price = furniture_price;
    }
}
