package lt.zilinskas.marius.testio.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import lt.zilinskas.marius.testio.TestioApplication;
import lt.zilinskas.marius.testio.api.entities.Token;

public class SharedPreferencesHelper {
    private static final String PREFERENCES_TAG = "app_preferences";
    private static final String LOGIN_TOKEN = "login_token";

    private static SharedPreferencesHelper instance;
    private SharedPreferences sharedPreferences;
    private TestioApplication application;
    private Token token;

    private SharedPreferencesHelper(TestioApplication application) {
        this.application = application;
        setupSharedPreference();
    }

    public static SharedPreferencesHelper getInstance(TestioApplication application) {
        if (instance == null) {
            instance = new SharedPreferencesHelper(application);
        }
        return instance;
    }

    private void setupSharedPreference() {
        sharedPreferences = application.getSharedPreferences(PREFERENCES_TAG, Context.MODE_PRIVATE);
    }

    public Token getToken() {
        if (token != null)
            return token;

        String encodedToken = sharedPreferences.getString(LOGIN_TOKEN, null);
        if (encodedToken == null) {
            return null;
        }

        token = new Gson().fromJson(encodedToken, Token.class);
        return token;
    }

    public void setToken(Token token) {
        sharedPreferences.edit().putString(LOGIN_TOKEN, new Gson().toJson(token)).apply();
        this.token = token;
    }
}
