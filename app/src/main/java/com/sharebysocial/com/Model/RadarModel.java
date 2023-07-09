package com.sharebysocial.com.Model;


public class RadarModel {
    private String userName, userImage, userId;
    private double lati, longi;

    public RadarModel() {
    }

    public RadarModel(String userName, String userImage, String userId, double lati, double longi) {
        this.userName = userName;
        this.userImage = userImage;
        this.userId = userId;
        this.lati = lati;
        this.longi = longi;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }
}
