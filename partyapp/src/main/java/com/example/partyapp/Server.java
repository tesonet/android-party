package com.example.partyapp;

import java.io.Serializable;

public class Server implements Serializable {
    public String name;
    public int distance;

    public Server(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }
}
