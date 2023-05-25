package com.example.shopease.model;

public class Top_ac {

    String ac_id, ac_imageUrl, ac_name;

    public Top_ac(){

    }

    public Top_ac(String ac_id, String ac_imageUrl, String ac_name){
        this.ac_id = ac_id;
        this.ac_imageUrl = ac_imageUrl;
        this.ac_name = ac_name;
    }

    public String getAc_id() {
        return ac_id;
    }

    public void setAc_id(String ac_id) {
        this.ac_id = ac_id;
    }

    public String getAc_imageUrl() {
        return ac_imageUrl;
    }

    public void setAc_imageUrl(String ac_imageUrl) {
        this.ac_imageUrl = ac_imageUrl;
    }

    public String getAc_name() {
        return ac_name;
    }

    public void setAc_name(String ac_name) {
        this.ac_name = ac_name;
    }
}
