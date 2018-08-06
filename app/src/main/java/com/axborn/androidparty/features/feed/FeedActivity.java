package com.axborn.androidparty.features.feed;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.axborn.androidparty.R;
import com.axborn.androidparty.features.database.DatabaseManager;
import com.axborn.androidparty.features.rest.RestManagerVolley;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {

    RestManagerVolley restManager = new RestManagerVolley();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
        ArrayList<HashMap<String, String>> serverList = databaseManager.getServers();
        if (serverList == null || serverList.isEmpty())
            new GetServerList().execute();
        else{
            ListAdapter adapter = new SimpleAdapter(
                    getApplicationContext(), serverList,
                    R.layout.list_item, new String[]{"name", "distance"},
                    new int[]{R.id.name, R.id.distance}) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    if(position == 0)
                        view.setBackgroundResource(R.drawable.main_header_selector);
                    else
                        view.setBackgroundResource(0);
                    return view;
                }
            };

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sorting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        ArrayList<HashMap<String, String>> serverList = Sorter.retrieveList((ListView)findViewById(R.id.list));
        switch (item.getItemId()) {
            case R.id.action_name_ascending:
                Sorter.sortAscending(serverList);
                break;
            case R.id.action_name_descending:
                Sorter.sortDescending(serverList);
                break;
        }

        ListAdapter adapter = new SimpleAdapter(
                getApplicationContext(), serverList,
                R.layout.list_item, new String[]{"name", "distance"},
                new int[]{R.id.name, R.id.distance}) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if(position == 0)
                    view.setBackgroundResource(R.drawable.main_header_selector);
                else
                    view.setBackgroundResource(0);
                return view;
            }
        };

        ((ListView)findViewById(R.id.list)).setAdapter(adapter);

        return true;
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




