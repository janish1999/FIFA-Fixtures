package com.example.user.fifa_fixtures;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class fixture implements Serializable {
    // int i;
    String country1;
    String country2;
    String date;
    String time;
    String venue;
    transient Uri icon1;
    transient Uri icon2;

    public fixture(String country1, String country2, String date, String time, String venue, Uri icon1, Uri icon2) {
        this.country1 = country1;
        this.country2 = country2;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.icon1 = icon1;
        this.icon2 = icon2;
    }

    public String getCountry1() {
        return country1;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public String getCountry2() {
        return country2;
    }

    public void setCountry2(String country2) {
        this.country2 = country2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Uri getIcon1() {
        return icon1;
    }

    public void setIcon1(Uri icon1) {
        this.icon1 = icon1;
    }

    public Uri getIcon2() {
        return icon2;
    }

    public void setIcon2(Uri icon2) {
        this.icon2 = icon2;
    }
}

