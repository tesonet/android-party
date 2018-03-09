package com.tesonet.example.android_party.model;

/**
 * Created by Vilius on 2018-03-08.
 */

public class TokenRequest {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
