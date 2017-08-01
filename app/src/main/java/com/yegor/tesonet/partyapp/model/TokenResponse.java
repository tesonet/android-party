package com.yegor.tesonet.partyapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Token response representation
 */
public class TokenResponse {

    @SerializedName("token")
    private String mToken;

    public String getToken() {
        return mToken;
    }
}
