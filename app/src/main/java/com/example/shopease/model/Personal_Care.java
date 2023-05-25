package com.example.shopease.model;

public class Personal_Care {

    String personal_care_id, personal_care_imageUrl, personal_care_name, personal_care_price, personal_care_weight,personal_care_rating,personal_care_discription,personal_care_stock;

    public Personal_Care(){

    }

    public Personal_Care(String personal_care_id, String personal_care_imageUrl, String personal_care_name , String personal_care_price , String personal_care_weight,String personal_care_rating,String personal_care_discription,String personal_care_stock){
        this.personal_care_id = personal_care_id;
        this.personal_care_imageUrl = personal_care_imageUrl;
        this.personal_care_name = personal_care_name;
        this.personal_care_price = personal_care_price;
        this.personal_care_weight = personal_care_weight;
        this.personal_care_rating = personal_care_rating;
        this.personal_care_discription = personal_care_discription;
        this.personal_care_stock = personal_care_stock;

    }

    public String getPersonal_care_stock() {
        return personal_care_stock;
    }

    public void setPersonal_care_stock(String personal_care_stock) {
        this.personal_care_stock = personal_care_stock;
    }

    public String getPersonal_care_weight() {
        return personal_care_weight;
    }

    public void setPersonal_care_weight(String personal_care_weight) {
        this.personal_care_weight = personal_care_weight;
    }

    public String getPersonal_care_rating() {
        return personal_care_rating;
    }

    public void setPersonal_care_rating(String personal_care_rating) {
        this.personal_care_rating = personal_care_rating;
    }

    public String getPersonal_care_discription() {
        return personal_care_discription;
    }

    public void setPersonal_care_discription(String personal_care_discription) {
        this.personal_care_discription = personal_care_discription;
    }

    public String getPersonal_care_id() {
        return personal_care_id;
    }

    public void setPersonal_care_id(String personal_care_id) {
        this.personal_care_id = personal_care_id;
    }

    public String getPersonal_care_imageUrl() {
        return personal_care_imageUrl;
    }

    public void setPersonal_care_imageUrl(String personal_care_imageUrl) {
        this.personal_care_imageUrl = personal_care_imageUrl;
    }

    public String getPersonal_care_name() {
        return personal_care_name;
    }

    public void setPersonal_care_name(String personal_care_name) {
        this.personal_care_name = personal_care_name;
    }

    public String getPersonal_care_price() {
        return personal_care_price;
    }

    public void setPersonal_care_price(String personal_care_price) {
        this.personal_care_price = personal_care_price;
    }
}
