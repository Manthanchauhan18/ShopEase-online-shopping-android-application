package com.example.shopease.model;

public class Products
{
    String gid;
    String imageUrl;
    String gName;
    int price;
    int stock;;

    public Products(){
        
    }

    public Products(String gid, String imageUrl, String gName, int price, int stock) {

        this.gid = gid;
        this.imageUrl = imageUrl;
        this.gName = gName;
        this.price = price;
        this.stock = stock;

    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
