package com.example.partyapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ServersActivity extends AppCompatActivity {

    public static String keyServers = "servers";
    ListView mServersListView;
    View mListHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servers);
        ArrayList<Server> servers = (ArrayList<Server>) getIntent().getSerializableExtra(keyServers);
        mServersListView = (ListView) findViewById(R.id.servers_list_view);
        mServersListView.setAdapter(new ServersAdapter(servers, this));
        mListHeader = findViewById(R.id.list_header);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mListHeader.setBackgroundColor(Color.WHITE);
            mListHeader.setElevation(20);
        }
    }
}
