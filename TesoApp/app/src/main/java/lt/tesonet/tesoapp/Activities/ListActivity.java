package lt.tesonet.tesoapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import lt.tesonet.tesoapp.Adapters.ServerAdapter;
import lt.tesonet.tesoapp.R;
import lt.tesonet.tesoapp.TesoApp;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        setContentView(R.layout.activity_list);
        ListView lstView = (ListView) findViewById(R.id.listViewServers);
        lstView.setAdapter(new ServerAdapter(getBaseContext(), R.layout.item_server, TesoApp.serverList));
        (findViewById(R.id.btnLogout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
