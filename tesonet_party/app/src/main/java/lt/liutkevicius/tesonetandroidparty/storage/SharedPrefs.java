package lt.liutkevicius.tesonetandroidparty.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import lt.liutkevicius.tesonetandroidparty.network.model.Server;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class SharedPrefs implements ISharedPrefs {
    private static final String PREF_FILE_NAME = "app_pref_file";
    private static final String KEY_TOKEN = "token";
    public static final String KEY_SERVERS = "serversList";

    private final SharedPreferences pref;
    private final Gson gson;

    @Inject
    public SharedPrefs(Context context, Gson gson) {
        this.pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    public void setToken(String token) {
        pref.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return pref.getString(KEY_TOKEN, null);
    }

    public void setServers(String serversJson) {
        pref.edit().putString(KEY_SERVERS, serversJson).apply();
    }

    public List<Server> getServers() {
        String serversJson = pref.getString(KEY_SERVERS, null);
        if (serversJson != null) {
            return Arrays.asList(gson.fromJson(serversJson, Server[].class));
        }
        return null;
    }

    public void clear() {
        pref.edit().clear().apply();
    }
}
