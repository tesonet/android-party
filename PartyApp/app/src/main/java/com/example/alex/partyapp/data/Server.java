package com.example.alex.partyapp.data;


import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 10.09.2017.
 */

public class Server {

    @SerializedName("name")
    private String name;

    @SerializedName("distance")
    private String distance;

    public String getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }
}
