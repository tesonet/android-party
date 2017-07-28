package com.yegor.tesonet.partyapp.framework;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.yegor.tesonet.partyapp.events.ServersFetchingSuccessEvent;
import com.yegor.tesonet.partyapp.model.Server;
import com.yegor.tesonet.partyapp.networking.ServiceProvider;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to work with storage
 */
public class DataProvider {

    public static final long DEFAULT_VALUE = 0;
    private static final String KEY_TIME = "KEY_TIME";
    private static final String KEY_LIST = "KEY_LIST";

    /**
     * stores token to preferences
     *
     * @param token token
     */
    public static void storeToken(String token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ApplicationSingleton.getInstance());
        preferences.edit().putString(ServiceProvider.KEY_TOKEN, token).apply();
    }

    /**
     * stores servers list to preferences
     *
     * @param servers list of servers
     */
    public static void storeList(List<Server> servers) {
        Gson gson = new Gson();
        String json = gson.toJson(servers);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ApplicationSingleton.getInstance());
        preferences.edit().putString(KEY_LIST, json).apply();
    }

    /**
     * stores last update time to preferences
     *
     * @param timestamp timestamp
     */
    public static void setLastUpdate(long timestamp) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ApplicationSingleton.getInstance());
        preferences.edit().putLong(KEY_TIME, timestamp).apply();
    }

    /**
     * @return last server update time
     */
    public static long getLastUpdate() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ApplicationSingleton.getInstance());
        return preferences.getLong(KEY_TIME, DEFAULT_VALUE);
    }

    /**
     * parses servers list from preferences
     */
    public static void loadInternal() {
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ApplicationSingleton.getInstance());
        ArrayList<Server> servers = new ArrayList<>();
        try {
            JSONArray jArray = new JSONArray(preferences.getString(KEY_LIST, ""));
            for (int i = 0; i < jArray.length(); i++) {
                servers.add(gson.fromJson(jArray.getString(i), Server.class));
            }
            EventBus.getDefault().post(new ServersFetchingSuccessEvent(servers));
        } catch (Exception e) {
            ApplicationSingleton.getInstance().getApi().loadExternal();
        }
    }
}
