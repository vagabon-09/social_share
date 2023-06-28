package com.sharebysocial.com.Model;

public class ProfileModel {
    private String accountName, userName;

    public ProfileModel() {
    }

    public ProfileModel(String accountName, String userName) {
        this.accountName = accountName;
        this.userName = userName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
