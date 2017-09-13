package com.example.alex.partyapp.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 03.09.2017.
 */

public class Token {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}
