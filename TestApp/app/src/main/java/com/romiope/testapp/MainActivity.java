package com.romiope.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.romiope.testapp.database.ServersDao;
import com.romiope.testapp.network.RestClient;
import com.romiope.testapp.network.entities.ServerResponse;
import com.romiope.testapp.network.v1.wrappers.ServersWrapper;

import java.util.Arrays;
import java.util.List;

import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private final ServersWrapper serversEndpoint = RestClient.getInstance().getApi().getServersApi();

    private RecyclerView list;
    private View listContainer;
    private View loadingContainer;

    public static void start(Context ctxt) {
        Intent intent = new Intent(ctxt, MainActivity.class);
        ctxt.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Animation rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
        rotation.setFillAfter(true);
        findViewById(R.id.iv_spinner).startAnimation(rotation);

        list = findViewById(R.id.rv_list);

        listContainer = findViewById(R.id.fl_list_container);
        loadingContainer = findViewById(R.id.fl_loading_container);
    }

    @Override
    protected void onResume() {
        super.onResume();

        list.setLayoutManager(new LinearLayoutManager(this));
        final MainListAdapter adapter = new MainListAdapter();
        list.setAdapter(adapter);

        final App application = (App) getApplication();
        final ServersDao serversDao = application.getDatabase().serversDao();

        final Thread loadFromDatabaseTask = new Thread(() -> {
            final List<ServerResponse> servers = serversDao.getAllServers();
            runOnUiThread(() -> adapter.addAll(servers));
            changeListVisibility(true);
        });
        loadFromDatabaseTask.start();

        final String token = TokenKeeper.getToken(getApplicationContext());

        subscriptions.add(serversEndpoint.getServers(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(response -> {
                            try {
                                loadFromDatabaseTask.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            final List<ServerResponse> servers = Arrays.asList(response.body());
                            serversDao.insertServers(servers);
                        },
                        throwable -> runOnUiThread(() -> {
                            Toast.makeText(this, throwable.toString(), Toast.LENGTH_LONG).show();
                            changeListVisibility(false);
                        }),
                        () -> {
                            final List<ServerResponse> servers = serversDao.getAllServers();
                            changeListVisibility(true);
                            list.post(() -> adapter.addAll(servers));
                        })
        );
    }

    private void changeListVisibility(boolean show) {
        runOnUiThread(() -> {
            loadingContainer.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
            listContainer.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        });
    }
}
