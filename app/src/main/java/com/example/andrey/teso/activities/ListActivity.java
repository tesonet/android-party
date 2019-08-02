package com.example.andrey.teso.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.andrey.teso.R;
import com.example.andrey.teso.servers.Server;
import com.example.andrey.teso.servers.ServerListAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<Server> serverList = new ArrayList<>();
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        serverList = intent.getParcelableArrayListExtra("servers");
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        listServers();

        Button backButton = findViewById(R.id.back_button);
        backButton.clearFocus();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity();
            }
        });
    }

    public void listServers() {
        ListView mListView = findViewById(R.id.listView);
        ServerListAdapter adapter = new ServerListAdapter(this, R.layout.adapter_view_layout, serverList);
        mListView.setAdapter(adapter);
    }

    private void launchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
