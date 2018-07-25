package com.tzemaitis.androidparty;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tzemaitis on 25/07/18.
 */

public class AuthenticationToken {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}
