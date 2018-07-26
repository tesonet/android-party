package com.svyd.tesonet.data.repository.servers.model;

import com.google.gson.annotations.SerializedName;

public class Server {

    @SerializedName("name")
    private String name;

    @SerializedName("distance")
    private int distance;

}
