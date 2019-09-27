package com.example.tesonet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesonet.R;
import com.example.tesonet.adapter.ServersAdapter;
import com.example.tesonet.database.models.Server;
import com.example.tesonet.viewmodels.ServerListViewModel;

import java.util.List;

public class ServerListActivity extends AppCompatActivity {
    ServerListViewModel serverListViewModel;
    ServersAdapter serversAdapter;
    ConstraintLayout serversListConstraintLayout;
    ConstraintLayout loadingConstraintLayout;
    TextView loaderTextView;
    String tokenString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);

        Intent intent = getIntent();
        tokenString = intent.getStringExtra("token");

        init();
    }

    public void logout(View view) {
        destroyActivity();
    }

    @Override
    public void onBackPressed() {
        //Paliekam taip, paspaudus atgal nieko neivyks.
        //Galima iskviesti alert dialog ir pasiulyti atsijungti.
    }

    private void init() {
        //RecyclerView
        final RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //ConstraintLayouts
        serversListConstraintLayout = findViewById(R.id.serverListConstraintLayout);
        loadingConstraintLayout = findViewById(R.id.loadingConstraintLayout);

        loadingScreen();

        //TextViews
        loaderTextView = findViewById(R.id.loaderTextView);

        //ViewModel
        serverListViewModel = ViewModelProviders.of(this).get(ServerListViewModel.class);
        serverListViewModel.setToken(tokenString);
        serverListViewModel.getServerList().observe(this, new Observer<List<Server>>() {
            @Override
            public void onChanged(@Nullable List<Server> servers) {
                serversAdapter = new ServersAdapter(ServerListActivity.this, servers);
                recyclerView.setAdapter(serversAdapter);
                serversListConstraintLayout.setVisibility(View.VISIBLE);
                loadingConstraintLayout.setVisibility(View.INVISIBLE);
            }
        });

        serverListViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("error")){
                    destroyActivity();
                }
            }
        });
    }

    public void loadingScreen() {
        //Loading screen init
        loadingConstraintLayout.setVisibility(View.VISIBLE);
        serversListConstraintLayout.setVisibility(View.INVISIBLE);
    }

    public void destroyActivity() {
        serverListViewModel.deleteServers();
        finish();
    }
}
