package com.example.shopease.model;

public class Offers {

    String gid;
    String imageUrl;
    String gName;

    public Offers(){

    }

    public Offers(String gid, String imageUrl, String gName) {

        this.gid = gid;
        this.imageUrl = imageUrl;
        this.gName = gName;

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
}
