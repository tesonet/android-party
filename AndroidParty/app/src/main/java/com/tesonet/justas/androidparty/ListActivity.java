package com.tesonet.justas.androidparty;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseActivity {
    ListView mListView;

    @Override
    protected void init() {
        super.init();
        initializeViews();
        initializeServer();
    }

    private void initializeViews() {
        setContentView(R.layout.activity_list);
        mListView = findViewById(R.id.list_view);
        ImageButton signOutButton = findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }
    private void initializeServer() {
        List<Pair<String, String>> servers = new ArrayList<>();

        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String serverJsonString = preferences.getString(SERVERS_PREFERENCE, "");
            JSONArray jsonArray = new JSONArray(serverJsonString);
            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                servers.add(new Pair<>(json.getString("name"), json.getString("distance")));
            }
        }
        catch(JSONException exception){
            logout();
        }

        ServerListAdapter appsAdapter = new ServerListAdapter(this, servers);
        mListView.setAdapter(appsAdapter);
    }

    private void logout() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().remove(SERVERS_PREFERENCE).apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
