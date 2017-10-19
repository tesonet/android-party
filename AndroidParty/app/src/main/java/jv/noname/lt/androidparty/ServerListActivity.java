package jv.noname.lt.androidparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import jv.noname.lt.androidparty.adapters.DistancesAdapter;

/**
 * Created by inter on 10/16/2017.
 */

public class ServerListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.distances_main);
        listView = (ListView) findViewById(R.id.distances_list_view);
        Intent intent = getIntent();
        HashMap<String, Integer> serverList = (HashMap<String, Integer>) intent.getExtras().get(RetrieveFeedTask.SERVER_LIST);
        DistancesAdapter adapter = new DistancesAdapter(serverList);
        listView.setAdapter(adapter);
        ImageView logoutButton = (ImageView) findViewById(R.id.imageLogout);
        logoutButton.setOnClickListener(new LogoutButtonOnClickListetener());

    }

    private class LogoutButtonOnClickListetener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            finish();
            System.exit(0);
        }
    }
}
