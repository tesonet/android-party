package dev.task.dainius.androidparty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
