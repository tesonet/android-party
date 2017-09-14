package com.example.alex.partyapp.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 03.09.2017.
 */

public class Account {
    @SerializedName("username")
    private final String username;

    @SerializedName("password")
    private final String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
