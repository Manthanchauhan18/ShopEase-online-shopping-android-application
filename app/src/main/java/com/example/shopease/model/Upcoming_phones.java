package com.example.shopease.model;

public class Upcoming_phones {

    String phone_id,imageUrl_phone, smartphone_name,smartphone_price,smartphone_storage,smartphone_rating , smartphone_discription , smartphone_summary , smartphone_stock;

    public Upcoming_phones(){

    }

    public Upcoming_phones(String phone_id , String imageUrl_phone, String smartphone_name) {

        this.phone_id = phone_id;
        this.imageUrl_phone = imageUrl_phone;
        this.smartphone_name = smartphone_name;

    }
    public Upcoming_phones(String phone_id , String imageUrl_phone, String smartphone_name , String smartphone_price, String smartphone_storage , String smartphone_rating , String smartphone_discription , String smartphone_summary , String smartphone_stock) {

        this.phone_id = phone_id;
        this.imageUrl_phone = imageUrl_phone;
        this.smartphone_name = smartphone_name;
        this.smartphone_price = smartphone_price;
        this.smartphone_storage = smartphone_storage;
        this.smartphone_rating = smartphone_rating;
        this.smartphone_discription = smartphone_discription;
        this.smartphone_summary = smartphone_summary;
        this.smartphone_stock = smartphone_stock;

    }

    public String getSmartphone_stock() {
        return smartphone_stock;
    }

    public void setSmartphone_stock(String smartphone_stock) {
        this.smartphone_stock = smartphone_stock;
    }

    public String getSmartphone_summary() {
        return smartphone_summary;
    }

    public void setSmartphone_summary(String smartphone_summary) {
        this.smartphone_summary = smartphone_summary;
    }

    public String getSmartphone_rating() {
        return smartphone_rating;
    }

    public void setSmartphone_rating(String smartphone_rating) {
        this.smartphone_rating = smartphone_rating;
    }

    public String getSmartphone_discription() {
        return smartphone_discription;
    }

    public void setSmartphone_discription(String smartphone_discription) {
        this.smartphone_discription = smartphone_discription;
    }

    public String getSmartphone_storage() {
        return smartphone_storage;
    }

    public void setSmartphone_storage(String smartphone_storage) {
        this.smartphone_storage = smartphone_storage;
    }

    public String getSmartphone_price() {
        return smartphone_price;
    }

    public void setSmartphone_price(String smartphone_price) {
        this.smartphone_price = smartphone_price;
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
    }

    public String getImageUrl_phone() {
        return imageUrl_phone;
    }

    public void setImageUrl_phone(String imageUrl_phone) {
        this.imageUrl_phone = imageUrl_phone;
    }

    public String getSmartphone_name() {
        return smartphone_name;
    }

    public void setSmartphone_name(String smartphone_name) {
        this.smartphone_name = smartphone_name;
    }
}
