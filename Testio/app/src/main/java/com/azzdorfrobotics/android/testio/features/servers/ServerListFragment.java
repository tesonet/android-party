package com.azzdorfrobotics.android.testio.features.servers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.azzdorfrobotics.android.testio.R;
import com.azzdorfrobotics.android.testio.di.ServerComponent;
import com.azzdorfrobotics.android.testio.features.auth.AuthCase;
import com.azzdorfrobotics.android.testio.features.shared.BaseFragment;
import com.azzdorfrobotics.android.testio.features.shared.MainNavigator;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Created on 03.01.2018.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class ServerListFragment extends BaseFragment {

    @BindView(R.id.rv_servers) RecyclerView rvServers;
    @BindView(R.id.srl_servers) SwipeRefreshLayout srlServers;

    @Inject ServerCase serverCase;
    @Inject AuthCase authCase;

    private MainNavigator mainNavigator;
    private ServerListAdapter serverListAdapter;

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainNavigator = (MainNavigator) getActivity();
        serverListAdapter = new ServerListAdapter(new ArrayList<>());
        rvServers.setAdapter(serverListAdapter);
        rvServers.setLayoutManager(new LinearLayoutManager(getContext()));
        srlServers.setOnRefreshListener(() -> {
            mainNavigator.showLoading(null, null);
        });
        subscribeToServerData();
    }

    @Override protected int getLayoutId() {
        return R.layout.fragment_server_list;
    }

    @Override protected void initializeInjector(Context context) {
        getComponent(ServerComponent.class, context).inject(ServerListFragment.this);
    }

    private void subscribeToServerData() {
        addScreenAliveDisposable(
            serverCase.getServers().subscribe(list -> {
                serverListAdapter.setData(list);
            }, throwable -> mainNavigator.showLoading(null, null)));
    }

    @OnClick(R.id.btn_logout) void logout() {
        authCase.logout();
        mainNavigator.showLogin();
    }
}
