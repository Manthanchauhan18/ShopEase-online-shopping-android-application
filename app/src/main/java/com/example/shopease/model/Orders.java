package com.example.shopease.model;

public class Orders {

    String order_id, transaction_id , product_name, imageUrl, product_price , product_quantity, user_address,timestrap;

    public Orders(){

    }

    public Orders(String order_id, String transaction_id , String imageUrl , String product_name, String product_price , String product_quantity, String user_address,String timestrap){
        this.order_id = order_id;
        this.transaction_id = transaction_id;
        this.imageUrl = imageUrl;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.user_address = user_address;
        this.timestrap = timestrap;

    }


    public String getTimestrap() {
        return timestrap;
    }

    public void setTimestrap(String timestrap) {
        this.timestrap = timestrap;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
}
