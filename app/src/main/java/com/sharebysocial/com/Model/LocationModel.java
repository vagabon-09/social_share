package com.sharebysocial.com.Model;

public class LocationModel {
    double lati, longi;
    long updateTime;

    public LocationModel() {
    }

    public LocationModel(double lati, double longi, long updateTime) {
        this.lati = lati;
        this.longi = longi;
        this.updateTime = updateTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
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
