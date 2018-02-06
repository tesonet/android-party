package aurimas.garuolis.tesonetparty;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import aurimas.garuolis.tesonetparty.adapters.ServerListAdapter;
import aurimas.garuolis.tesonetparty.data.DbHelper;
import aurimas.garuolis.tesonetparty.data.PartyContract;
import aurimas.garuolis.tesonetparty.utils.ApiUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends AppCompatActivity {
    public static final String TAG = ListActivity.class.toString();
    private SQLiteDatabase mDb;
    private ServerListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // remove shadow from ActionBar
        getSupportActionBar().setElevation(0);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ServerListAdapter();
        mRecyclerView.setAdapter(mAdapter);


        mDb = new DbHelper(this).getReadableDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryCachedServerList();
    }

    private void queryCachedServerList(){
        Cursor cursor = mDb.query(PartyContract.ServerEntry.TABLE_NAME, null, null, null, null, null, null);
        mAdapter.reloadCursor(cursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.server_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_logout:
                showLoginActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
