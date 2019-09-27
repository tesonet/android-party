package com.example.tesonet.database.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "token_table")
public class Token {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String token;

    public Token(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
