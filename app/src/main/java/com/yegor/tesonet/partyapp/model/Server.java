package com.yegor.tesonet.partyapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Server representation
 */
public class Server {

    @SerializedName("name")
    private String mName;

    @SerializedName("distance")
    private String mDistance;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDistance() {
        return mDistance;
    }

    public void setDistance(String mDistance) {
        this.mDistance = mDistance;
    }
}
