package com.example.shopease.model;

public class Cart_product_layout {

    String cart_id;
    String cart_imageUrl;
    String cart_Name,cart_rating;
    String cart_price,cart_stock;

    public Cart_product_layout(){

    }

    public Cart_product_layout(String cart_id, String cart_imageUrl, String cart_Name, String cart_price,String cart_rating) {

        this.cart_id = cart_id;
        this.cart_imageUrl = cart_imageUrl;
        this.cart_Name = cart_Name;
        this.cart_price = cart_price;
        this.cart_rating = cart_rating;

    }

    public Cart_product_layout(String cart_id, String cart_imageUrl, String cart_Name, String cart_price,String cart_rating , String cart_stock) {

        this.cart_id = cart_id;
        this.cart_imageUrl = cart_imageUrl;
        this.cart_Name = cart_Name;
        this.cart_price = cart_price;
        this.cart_rating = cart_rating;
        this.cart_stock = cart_stock;

    }

    public String getCart_stock() {
        return cart_stock;
    }

    public void setCart_stock(String cart_stock) {
        this.cart_stock = cart_stock;
    }

    public String getCart_price() {
        return cart_price;
    }

    public String getCart_rating() {
        return cart_rating;
    }

    public void setCart_rating(String cart_rating) {
        this.cart_rating = cart_rating;
    }

    public void setCart_price(String cart_price) {
        this.cart_price = cart_price;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getCart_imageUrl() {
        return cart_imageUrl;
    }

    public void setCart_imageUrl(String cart_imageUrl) {
        this.cart_imageUrl = cart_imageUrl;
    }

    public String getCart_Name() {
        return cart_Name;
    }

    public void setCart_Name(String cart_Name) {
        this.cart_Name = cart_Name;
    }
}
