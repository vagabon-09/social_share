package com.sharebysocial.com.Model;

public class ProfileModel {
    private String sName, userName;

    public ProfileModel() {
    }

    public ProfileModel(String sName, String userName) {
        this.sName = sName;
        this.userName = userName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
