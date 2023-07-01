package com.sharebysocial.com.Model;

public class LocationModel {
    double lati, longi;

    public LocationModel() {
    }

    public LocationModel(double lati, double longi) {
        this.lati = lati;
        this.longi = longi;
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
