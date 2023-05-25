package com.example.shopease.model;


//user model which is pass and get data from firebase realtime storage having a name as "User"

public class User {
    String name , email , mobile , password , user_id;

    public User(){

    }


    public User(String user_id , String name, String email , String mobile, String password) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    //getter and setter methods

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String toString(){
        return "Name = "+ name + "\n mobile = "+mobile+ "\n Email = " + email + "\n Password = " + password + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
