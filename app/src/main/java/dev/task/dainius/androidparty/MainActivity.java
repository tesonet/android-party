package dev.task.dainius.androidparty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView viewServerList;
    private List<Server> serverList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#ffb2b2b2")));
        getSupportActionBar().setElevation(0);

        viewServerList = findViewById(R.id.server_list);
        final ServerListAdapter listAdapter = new ServerListAdapter(this, R.layout.server_list_row, serverList);
        viewServerList.setAdapter(listAdapter);

        DataManager.getInstance(this).getServerList(new DataManager.VolleyCallback() {
            @Override
            public void onSuccess(List<Server> result) {
                serverList = result;
                listAdapter.clear();
                listAdapter.addAll(serverList);
                listAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Clear token and go back to login screen
            case R.id.logout:

                DataManager.getInstance(this).setToken(null);

                Intent i = new Intent(this, LoginActivity.class);
                this.startActivity(i);

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
