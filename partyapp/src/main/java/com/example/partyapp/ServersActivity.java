package com.example.partyapp;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.partyapp.Adapters.ServersAdapter;
import com.example.partyapp.Database.DatabaseHelper;
import com.example.partyapp.Models.Server;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServersActivity extends AppCompatActivity {

    public static String keyServers = "servers";
    ListView mServersListView;
    View mListHeader;
    private DatabaseHelper databaseHelper;

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

        getSupportActionBar().show();
        setupActionBar();

        //Bonus: implement persistent storage of the downloaded server data
        saveServers(servers);

        //test
        printSavedServers();

    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayUseLogoEnabled(false);
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(false);

        actionBar.setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);

        View view = actionBar.getCustomView();

        ImageButton logoutButton = view.findViewById(R.id.logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        actionBar.setCustomView(customNav, lp1);
    }

    private void printSavedServers() {
        try {
            DatabaseHelper dbHelper = getHelper();
            Dao<Server, Integer> serverDao = dbHelper.getServersDao();
            ArrayList<Server> serversFromDb = new ArrayList<>(serverDao.queryForAll());
            String serversString = "";
            for (Server server:
                 serversFromDb) {
                serversString += server.toString() + "\n";
            }
            Log.w(ServersActivity.class.toString(), serversString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveServers(List<Server> servers) {
        try {
            DatabaseHelper dbHelper = getHelper();
            Dao<Server, Integer> serverDao = dbHelper.getServersDao();
            dbHelper.clearServersTable();
            serverDao.create(servers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
