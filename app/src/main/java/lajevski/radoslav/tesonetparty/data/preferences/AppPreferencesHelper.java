package lajevski.radoslav.tesonetparty.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import lajevski.radoslav.tesonetparty.di.ApplicationContext;
import lajevski.radoslav.tesonetparty.utils.AppString;

/**
 * Created by Radoslav on 1/16/2018.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    public static final String AUTHORIZATION_TOKEN_PREFIX = "Bearer ";

    public static String PREFERENCES_NAME = "tesonet_prefs";

    public static String PREF_TOKEN = "pref_token";

    private final SharedPreferences mPreferences;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void setToken(String token) {
        mPreferences.edit().putString(PREF_TOKEN, token).apply();
    }

    @Override
    public String getToken() {
        String token = mPreferences.getString(PREF_TOKEN, null);
        return AppString.isEmpty(token) ? null : AUTHORIZATION_TOKEN_PREFIX + token;
    }
}
