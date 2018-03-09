package com.tesonet.example.android_party.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tesonet.example.android_party.model.ExListItem;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Vilius on 2018-03-07.
 */

public class Preferences {
    private static final String PREFERENCE_FILE = "ExampleAppPreferences";

    private static final String PROPERTY_TOKEN = "user_token";
    private static final String PROPERTY_EX_LIST = "ex_list";

    private Preferences() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    public static void storeTokenValue(Context context, String value) {
        final SharedPreferences prefs = getSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_TOKEN, value);
        editor.apply();
    }

    public static String getTokenValue(Context context) {
        final SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getString(PROPERTY_TOKEN, "");
    }

    public static void storeExList(Context context, ArrayList<ExListItem> values) {
        final SharedPreferences prefs = getSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(values);
        editor.putString(PROPERTY_EX_LIST, json);
        editor.apply();
    }

    public static ArrayList<ExListItem> getExList(Context context) {
        final SharedPreferences prefs = getSharedPreferences(context);
        String json = prefs.getString(PROPERTY_EX_LIST, null);
        Type listType = new TypeToken<ArrayList<ExListItem>>(){}.getType();
        return new Gson().fromJson(json, listType);
    }
}
