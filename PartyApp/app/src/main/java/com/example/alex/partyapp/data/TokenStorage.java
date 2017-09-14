package com.example.alex.partyapp.data;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class TokenStorage {
    private static final String TOKEN_ID = "Token";

    private final SharedPreferences preferences;

    @Inject
    public TokenStorage(SharedPreferences preferences){
        this.preferences = preferences;
    }

    public void store(String token){
        preferences.edit().putString(TOKEN_ID, token).apply();
    }


    public String load(){
        return preferences.getString(TOKEN_ID, null);
    }
}
