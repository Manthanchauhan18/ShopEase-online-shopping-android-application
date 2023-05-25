package com.example.shopease.model;

public class Address {

    String address_id, name , full_address , mobile;

    public Address(){

    }

    public Address(String address_id,String name , String full_address , String mobile){

        this.address_id = address_id;
        this.name = name ;
        this.full_address = full_address;
        this.mobile = mobile;

    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
