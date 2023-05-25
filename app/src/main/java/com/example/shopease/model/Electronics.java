package com.example.shopease.model;

public class Electronics {

    String electronics_id,imageUrl_electronics, electronics_name,electronics_price,electronics_rating , electronics_discription , electronics_stock;

    public Electronics(){

    }

    public Electronics(String electronics_id , String imageUrl_electronics, String electronics_name) {

        this.electronics_id = electronics_id;
        this.imageUrl_electronics = imageUrl_electronics;
        this.electronics_name = electronics_name;

    }

    public Electronics(String electronics_id , String imageUrl_electronics, String electronics_name , String electronics_price , String electronics_rating , String electronics_discription , String electronics_stock) {

        this.electronics_id = electronics_id;
        this.imageUrl_electronics = imageUrl_electronics;
        this.electronics_name = electronics_name;
        this.electronics_price = electronics_price;
        this.electronics_rating = electronics_rating;
        this.electronics_discription = electronics_discription;
        this.electronics_stock = electronics_stock;

    }

    public String getElectronics_stock() {
        return electronics_stock;
    }

    public void setElectronics_stock(String electronics_stock) {
        this.electronics_stock = electronics_stock;
    }

    public String getElectronics_id() {
        return electronics_id;
    }

    public void setElectronics_id(String electronics_id) {
        this.electronics_id = electronics_id;
    }

    public String getImageUrl_electronics() {
        return imageUrl_electronics;
    }

    public void setImageUrl_electronics(String imageUrl_electronics) {
        this.imageUrl_electronics = imageUrl_electronics;
    }

    public String getElectronics_name() {
        return electronics_name;
    }

    public void setElectronics_name(String electronics_name) {
        this.electronics_name = electronics_name;
    }

    public String getElectronics_price() {
        return electronics_price;
    }

    public void setElectronics_price(String electronics_price) {
        this.electronics_price = electronics_price;
    }

    public String getElectronics_rating() {
        return electronics_rating;
    }

    public void setElectronics_rating(String electronics_rating) {
        this.electronics_rating = electronics_rating;
    }

    public String getElectronics_discription() {
        return electronics_discription;
    }

    public void setElectronics_discription(String electronics_discription) {
        this.electronics_discription = electronics_discription;
    }
}
