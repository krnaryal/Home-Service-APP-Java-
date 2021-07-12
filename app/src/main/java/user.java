package com.example.myapplication;

class user {

    String UserId;
    String FullName;
    String UserPhoneNo;
    String UserEmail;


    public user(String userId, String userName, String userPhoneNo, String userEmail, String userPassword) {
        UserId = userId;
        FullName = userName;
        UserEmail = userEmail;
        UserPhoneNo = userPhoneNo;

    }

    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return FullName;
    }

    public String getUserPhoneNo() {
        return UserPhoneNo;
    }

    public String getUserEmail() {
        return UserEmail;
    }


}
