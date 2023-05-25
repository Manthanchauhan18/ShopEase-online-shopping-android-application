package com.example.shopease.model;

public class Deals {

    String deals_id , imageUrl, deals_name,deals_price,deals_rating,deals_discription,deals_stock,deals_discount;

    public Deals(){

    }

    public Deals(String deals_id, String imageUrl, String deals_name,String deals_price,String deals_rating,String deals_discription,String deals_stock,String deals_discount) {
        this.deals_id = deals_id;
        this.imageUrl = imageUrl;
        this.deals_name = deals_name;
        this.deals_price = deals_price;
        this.deals_rating = deals_rating;
        this.deals_discription = deals_discription;
        this.deals_stock = deals_stock;
        this.deals_discount = deals_discount;

    }

    public String getDeals_discount() {
        return deals_discount;
    }

    public void setDeals_discount(String deals_discount) {
        this.deals_discount = deals_discount;
    }

    public String getDeals_price() {
        return deals_price;
    }

    public void setDeals_price(String deals_price) {
        this.deals_price = deals_price;
    }

    public String getDeals_rating() {
        return deals_rating;
    }

    public void setDeals_rating(String deals_rating) {
        this.deals_rating = deals_rating;
    }

    public String getDeals_discription() {
        return deals_discription;
    }

    public void setDeals_discription(String deals_discription) {
        this.deals_discription = deals_discription;
    }

    public String getDeals_stock() {
        return deals_stock;
    }

    public void setDeals_stock(String deals_stock) {
        this.deals_stock = deals_stock;
    }

    public String getDeals_id() {
        return deals_id;
    }

    public void setDeals_id(String deals_id) {
        this.deals_id = deals_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDeals_name() {
        return deals_name;
    }

    public void setDeals_name(String deals_name) {
        this.deals_name = deals_name;
    }
}
