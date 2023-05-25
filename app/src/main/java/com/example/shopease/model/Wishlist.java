package com.example.shopease.model;

public class Wishlist {

    String wishlist_id;
    String wishlist_imageUrl;
    String wishlist_Name,wishlist_rating;
    String wishlist_price,wishlist_stock;

    public Wishlist(){

    }

    public Wishlist(String wishlist_id, String wishlist_imageUrl, String wishlist_Name, String wishlist_price,String wishlist_rating) {

        this.wishlist_id = wishlist_id;
        this.wishlist_imageUrl = wishlist_imageUrl;
        this.wishlist_Name = wishlist_Name;
        this.wishlist_price = wishlist_price;
        this.wishlist_rating = wishlist_rating;

    }

    public String getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(String wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public String getWishlist_imageUrl() {
        return wishlist_imageUrl;
    }

    public void setWishlist_imageUrl(String wishlist_imageUrl) {
        this.wishlist_imageUrl = wishlist_imageUrl;
    }

    public String getWishlist_Name() {
        return wishlist_Name;
    }

    public void setWishlist_Name(String wishlist_Name) {
        this.wishlist_Name = wishlist_Name;
    }

    public String getWishlist_rating() {
        return wishlist_rating;
    }

    public void setWishlist_rating(String wishlist_rating) {
        this.wishlist_rating = wishlist_rating;
    }

    public String getWishlist_price() {
        return wishlist_price;
    }

    public void setWishlist_price(String wishlist_price) {
        this.wishlist_price = wishlist_price;
    }

    public String getWishlist_stock() {
        return wishlist_stock;
    }

    public void setWishlist_stock(String wishlist_stock) {
        this.wishlist_stock = wishlist_stock;
    }
}
