package lt.liutkevicius.tesonetandroidparty.storage;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPrefs {
    private static final String PREF_FILE_NAME = "app_pref_file";
    private static final String KEY_TOKEN = "token";

    private final SharedPreferences pref;

    @Inject
    public SharedPrefs(Context context) {
        this.pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        pref.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return pref.getString(KEY_TOKEN, null);
    }
}
