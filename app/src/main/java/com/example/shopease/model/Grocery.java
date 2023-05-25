package com.example.shopease.model;

public class Grocery {

    String grocery_id, grocery_imageUrl, grocery_name, grocery_price, grocery_amount,grocery_rating,grocery_discription,grocery_stock;

    public Grocery(){

    }

    public Grocery(String grocery_id, String grocery_imageUrl, String grocery_name){
        this.grocery_id = grocery_id;
        this.grocery_imageUrl = grocery_imageUrl;
        this.grocery_name = grocery_name;

    }

    public Grocery(String grocery_id, String grocery_imageUrl, String grocery_name , String grocery_price , String grocery_amount , String grocery_rating , String grocery_discription , String grocery_stock){
        this.grocery_id = grocery_id;
        this.grocery_imageUrl = grocery_imageUrl;
        this.grocery_name = grocery_name;
        this.grocery_price = grocery_price;
        this.grocery_amount = grocery_amount;
        this.grocery_rating = grocery_rating;
        this.grocery_discription = grocery_discription;
        this.grocery_stock = grocery_stock;

    }

    public String getGrocery_stock() {
        return grocery_stock;
    }

    public void setGrocery_stock(String grocery_stock) {
        this.grocery_stock = grocery_stock;
    }

    public String getGrocery_rating() {
        return grocery_rating;
    }

    public void setGrocery_rating(String grocery_rating) {
        this.grocery_rating = grocery_rating;
    }

    public String getGrocery_discription() {
        return grocery_discription;
    }

    public void setGrocery_discription(String grocery_discription) {
        this.grocery_discription = grocery_discription;
    }

    public String getGrocery_price() {
        return grocery_price;
    }

    public void setGrocery_price(String grocery_price) {
        this.grocery_price = grocery_price;
    }

    public String getGrocery_amount() {
        return grocery_amount;
    }

    public void setGrocery_amount(String grocery_amount) {
        this.grocery_amount = grocery_amount;
    }

    public String getGrocery_id() {
        return grocery_id;
    }

    public void setGrocery_id(String grocery_id) {
        this.grocery_id = grocery_id;
    }

    public String getGrocery_imageUrl() {
        return grocery_imageUrl;
    }

    public void setGrocery_imageUrl(String grocery_imageUrl) {
        this.grocery_imageUrl = grocery_imageUrl;
    }

    public String getGrocery_name() {
        return grocery_name;
    }

    public void setGrocery_name(String grocery_name) {
        this.grocery_name = grocery_name;
    }
}
