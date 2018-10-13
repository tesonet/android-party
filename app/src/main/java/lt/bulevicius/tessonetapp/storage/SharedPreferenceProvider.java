package lt.bulevicius.tessonetapp.storage;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import static lt.bulevicius.tessonetapp.app.Constants.KEY_TOKEN;

/**
 * The type Shared preference provider.
 */
public final class SharedPreferenceProvider implements LocalDataProvider {

    private final SharedPreferences sharedPreferences;

    /**
     * Instantiates a new Shared preference provider.
     *
     * @param sharedPreferences the shared preferences
     */
    public SharedPreferenceProvider(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void setToken(String token) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).commit();
    }

    @Override
    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }
}
