package com.example.partyapp.Models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class Server implements Serializable {

    @DatabaseField(generatedId = true, columnName = "server_id")
    public long serverId;

    @DatabaseField(columnName = "name")
    public String name;

    @DatabaseField(columnName = "distance")
    public int distance;

    public Server(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    //for sqlite
    public Server(){}

    @Override
    public String toString() {
        return name + ", " + distance;
    }
}
