package com.axborn.androidparty.features.feed;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.axborn.androidparty.R;
import com.axborn.androidparty.features.database.DatabaseManager;
import com.axborn.androidparty.features.rest.RestManager;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {

    RestManager restManager = new RestManager();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
        ArrayList<HashMap<String, String>> serverList = databaseManager.getServers();
        if (serverList == null || serverList.isEmpty())
            new GetServerList().execute();
        else{
            ListAdapter adapter = new SimpleAdapter(
                    getApplicationContext(), serverList,
                    R.layout.list_item, new String[]{"name", "distance"},
                    new int[]{R.id.name, R.id.distance});

            ((ListView)findViewById(R.id.list)).setAdapter(adapter);
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetServerList().execute();
            }
        });

    }


    private class GetServerList extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(FeedActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            restManager.initiateRestCall(getApplicationContext(), (ListView) findViewById(R.id.list));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
        }

    }
}




