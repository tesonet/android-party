package com.azzdorfrobotics.android.testio.features.shared;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import com.azzdorfrobotics.android.testio.App;
import com.azzdorfrobotics.android.testio.R;
import com.azzdorfrobotics.android.testio.di.HasComponent;
import com.azzdorfrobotics.android.testio.di.ServerComponent;
import com.azzdorfrobotics.android.testio.features.auth.LoginFragment;
import com.azzdorfrobotics.android.testio.features.servers.LoadingFragment;
import com.azzdorfrobotics.android.testio.features.servers.ServerListFragment;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class MainActivity extends BaseActivity
    implements HasComponent<ServerComponent>, MainNavigator {

    private static final int LOGIN = 1 << 1;
    private static final int LOADING = 1 << 2;
    private static final int SERVERS = 1 << 3;
    @IntDef({
        LOGIN, LOADING, SERVERS
    }) @interface StackState {
    }

    private ServerComponent serverComponent;
    private @StackState int stackState;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            showLogin();
        }
    }

    @Override protected void initializeInjector() {
        serverComponent = App.get(this).getAppComponent().serverComponent();
        serverComponent.inject(this);
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_fragment_container;
    }

    @Override public ServerComponent getComponent() {
        return serverComponent;
    }

    @Override public void showLogin() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.fragment_container, new LoginFragment())
            .commit();
        stackState = LOGIN;
    }

    @Override public void showLoading(String name, String password) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.fragment_container, LoadingFragment.getInstance(name, password))
            .commit();
        stackState = LOADING;
    }

    @Override public void showServerList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.fragment_container, new ServerListFragment())
            .commit();
        stackState = SERVERS;
    }

    @Override public void onBackPressed() {
        switch (stackState) {
            default:
            case LOGIN:
                super.onBackPressed();
                break;
            case LOADING:
                showLogin();
                break;
            case SERVERS:
                showLogin();
                break;
        }
    }
}
