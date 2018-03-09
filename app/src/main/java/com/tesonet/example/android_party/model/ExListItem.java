package com.tesonet.example.android_party.model;

/**
 * Created by Vilius on 2018-03-09.
 */

public class ExListItem {
    private String name;
    private int distance;

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

    @Override
    public String toString() {
        return "Name: "+getName()+" distance: "+getDistance();
    }
}
