package com.tzemaitis.androidparty;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tzemaitis on 25/07/18.
 */

public class UserAccount {
    @SerializedName("username")
    private final String username;

    @SerializedName("password")
    private final String password;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
