package com.example.tesonet;

public class User {

    private String token;

    public User(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }
}
