package com.example.tesonet.database.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "servers_table")
public class Server {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int distance;

    public Server(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
