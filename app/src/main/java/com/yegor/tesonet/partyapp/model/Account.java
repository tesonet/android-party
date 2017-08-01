package com.yegor.tesonet.partyapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Account representation
 */
public class Account {

    @SerializedName("username")
    private String mUsername;

    @SerializedName("password")
    private String mPassword;

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
