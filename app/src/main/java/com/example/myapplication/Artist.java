package com.example.myapplication;

public class Artist {
    String UserId;
    String UserName;
    String UserLocation;
    String UserPhone;
    String userChoose;

    public Artist() {

    }

    public Artist(String userId, String userName, String userLocation, String userPhone, String userChoose) {
        UserId = userId;
        UserName = userName;
        UserLocation = userLocation;
        UserPhone = userPhone;
        this.userChoose = userChoose;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserLocation() {
        return UserLocation;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public String getUserChoose() {
        return userChoose;
    }
}
