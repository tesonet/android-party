package com.example.alex.partyapp.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

//todo DAO
public class ServersStorage {
    private static final String SERVERS_ID = "Servers";

    private final SharedPreferences preferences;

    @Inject
    public ServersStorage(SharedPreferences preferences){
        this.preferences = preferences;
    }

    public void store(List<Server> servers){
        Gson gson = new Gson();
        preferences.edit().putString(SERVERS_ID, gson.toJson(servers)).apply();
    }

    public List<Server> load(){
        Gson gson = new Gson();
        return gson.fromJson(preferences.getString(SERVERS_ID, null),
                new TypeToken<List<Server>>(){}.getType());
    }
}
