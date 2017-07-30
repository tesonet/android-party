package com.romiope.testapp;

import android.content.Context;

public final class TokenKeeper {

    private TokenKeeper() {}

    public static final String tokenPrefsId = "TOKEN";

    public static void setToken(Context ctxt, String token) {
        ctxt.getSharedPreferences("prefs", Context.MODE_PRIVATE)
                .edit()
                .putString(tokenPrefsId, token)
                .apply();
    }

    public static String getToken(Context ctxt) {
        return ctxt.getSharedPreferences("prefs", Context.MODE_PRIVATE).getString(tokenPrefsId, "");
    }
}
