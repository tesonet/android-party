package com.axborn.androidparty.features.feed;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.axborn.androidparty.R;
import com.axborn.androidparty.features.authentication.LoginActivity;
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
        else
            refreshFeed(serverList);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetServerList().execute();
            }
        });

        ImageView imageButton = (ImageView) toolbar.findViewById(R.id.logout);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedActivity.this, LoginActivity.class);
                FeedActivity.this.startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
        switch (item.getItemId()) {
            case R.id.action_name_ascending:
                ArrayList<HashMap<String, String>> serverList = Sorter.retrieveList((ListView)findViewById(R.id.list));
                Sorter.sortAscending(serverList);
                refreshFeed(serverList);
                break;
            case R.id.action_name_descending:
                ArrayList<HashMap<String, String>> serverList2 = Sorter.retrieveList((ListView)findViewById(R.id.list));
                Sorter.sortDescending(serverList2);
                refreshFeed(serverList2);
                break;
        }


        return true;
    }

    private void refreshFeed(ArrayList<HashMap<String, String>> serverList){
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



    private class GetServerList extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress(true);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            restManager.initiateRestCall(getApplicationContext(), (ListView) findViewById(R.id.list));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            showProgress(false);
        }

    }

    View progressView;
    View loginFormView;
    View progressDescriptionView;
    View toolbarView;
    private void showProgress(final boolean show) {

        loginFormView = findViewById(R.id.feed_content_form);
        progressView = findViewById(R.id.feed_progress_bar);
        progressDescriptionView = findViewById(R.id.feed_progress_description);
        toolbarView = findViewById(R.id.toolbar);

        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        toolbarView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressDescriptionView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });

    }
}




