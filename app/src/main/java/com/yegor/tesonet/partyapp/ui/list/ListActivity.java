package com.yegor.tesonet.partyapp.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yegor.tesonet.partyapp.R;
import com.yegor.tesonet.partyapp.events.LoadStartEvent;
import com.yegor.tesonet.partyapp.events.ServersFetchingSuccessEvent;
import com.yegor.tesonet.partyapp.framework.ApplicationSingleton;
import com.yegor.tesonet.partyapp.framework.BaseActivity;
import com.yegor.tesonet.partyapp.ui.login.LoginActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * screen with servers list
 */
public class ListActivity extends BaseActivity {

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ServersListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mUnBinder = ButterKnife.bind(this);
        initList();
        initToolbar();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadServers();
    }

    private void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        mAdapter = new ServersListAdapter(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        list.addItemDecoration(dividerItemDecoration);
        list.setAdapter(mAdapter);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void loadServers() {
        ApplicationSingleton.getInstance().getApi().loadServers();
    }

    @OnClick(R.id.logout)
    @SuppressWarnings("unused")
    void logout() {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Subscribe
    @SuppressWarnings("unused")
    void onLoadStart(LoadStartEvent event) {
        showWaitDialog("Fetching the list...");
    }

    @Subscribe
    @SuppressWarnings("unused")
    void onLoadDone(ServersFetchingSuccessEvent event) {
        hideWaitDialog();
        mAdapter.setServers(event.getServers());
    }
}
