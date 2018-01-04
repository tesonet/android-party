package com.azzdorfrobotics.android.testio.preferences;

import android.content.SharedPreferences;
import io.reactivex.annotations.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

@Singleton public class AppPreferences {

    private final SharedPreferences sharedPreferences;

    @Inject public AppPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    private String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void setUserName(@Nullable String name) {
        setString(Key.CREDENTIALS_USER_NAME, name);
    }

    public @Nullable String getUserName() {
        return getString(Key.CREDENTIALS_USER_NAME, null);
    }

    public void setUserToken(@Nullable String name) {
        setString(Key.CREDENTIALS_USER_TOKEN, name);
    }

    public @Nullable String getUserToken() {
        return getString(Key.CREDENTIALS_USER_TOKEN, null);
    }
}
