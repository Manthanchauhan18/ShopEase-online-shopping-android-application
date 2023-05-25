package com.example.shopease.model;

public class Books {

    String books_id, books_imageUrl, books_name , books_price,books_rating,books_discription,books_stock;

    public Books(){

    }

    public Books(String books_id, String books_imageUrl, String books_name,String books_price,String books_rating,String books_discription,String books_stock){
        this.books_id = books_id;
        this.books_name = books_name;
        this.books_price = books_price;
        this.books_imageUrl = books_imageUrl;
        this.books_rating = books_rating;
        this.books_discription = books_discription;
        this.books_stock = books_stock;
    }

    public String getBooks_stock() {
        return books_stock;
    }

    public void setBooks_stock(String books_stock) {
        this.books_stock = books_stock;
    }

    public String getBooks_rating() {
        return books_rating;
    }

    public void setBooks_rating(String books_rating) {
        this.books_rating = books_rating;
    }

    public String getBooks_discription() {
        return books_discription;
    }

    public void setBooks_discription(String books_discription) {
        this.books_discription = books_discription;
    }

    public String getBooks_id() {
        return books_id;
    }

    public void setBooks_id(String books_id) {
        this.books_id = books_id;
    }

    public String getBooks_imageUrl() {
        return books_imageUrl;
    }

    public void setBooks_imageUrl(String books_imageUrl) {
        this.books_imageUrl = books_imageUrl;
    }

    public String getBooks_name() {
        return books_name;
    }

    public void setBooks_name(String books_name) {
        this.books_name = books_name;
    }

    public String getBooks_price() {
        return books_price;
    }

    public void setBooks_price(String books_price) {
        this.books_price = books_price;
    }
}
