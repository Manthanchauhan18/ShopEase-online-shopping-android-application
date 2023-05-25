package com.example.shopease.model;

public class Pharmacy {

    String pharmacy_id, pharmacy_imageUrl, pharmacy_name, pharmacy_price, pharmacy_rating,pharmacy_discription,pharmacy_stock;

    public Pharmacy(){

    }

    public Pharmacy(String pharmacy_id, String pharmacy_imageUrl, String pharmacy_name , String pharmacy_price , String pharmacy_rating,String pharmacy_discription, String pharmacy_stock){
        this.pharmacy_id = pharmacy_id;
        this.pharmacy_imageUrl = pharmacy_imageUrl;
        this.pharmacy_name = pharmacy_name;
        this.pharmacy_price = pharmacy_price;
        this.pharmacy_rating = pharmacy_rating;
        this.pharmacy_discription = pharmacy_discription;
        this.pharmacy_stock = pharmacy_stock;

    }

    public String getPharmacy_stock() {
        return pharmacy_stock;
    }

    public void setPharmacy_stock(String pharmacy_stock) {
        this.pharmacy_stock = pharmacy_stock;
    }

    public String getPharmacy_rating() {
        return pharmacy_rating;
    }

    public void setPharmacy_rating(String pharmacy_rating) {
        this.pharmacy_rating = pharmacy_rating;
    }

    public String getPharmacy_discription() {
        return pharmacy_discription;
    }

    public void setPharmacy_discription(String pharmacy_discription) {
        this.pharmacy_discription = pharmacy_discription;
    }

    public String getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(String pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public String getPharmacy_imageUrl() {
        return pharmacy_imageUrl;
    }

    public void setPharmacy_imageUrl(String pharmacy_imageUrl) {
        this.pharmacy_imageUrl = pharmacy_imageUrl;
    }

    public String getPharmacy_name() {
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        this.pharmacy_name = pharmacy_name;
    }

    public String getPharmacy_price() {
        return pharmacy_price;
    }

    public void setPharmacy_price(String pharmacy_price) {
        this.pharmacy_price = pharmacy_price;
    }


}
