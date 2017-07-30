package com.romiope.testapp.network.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity
public class ServerResponse {

    @Expose
    @PrimaryKey(autoGenerate = true)
    public long uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "distance")
    public String distance;
}
