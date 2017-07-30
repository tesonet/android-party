package com.romiope.testapp.network.entities;

public class LoginRequest {

    String username;
    String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}