package com.tzemaitis.androidparty;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener,
                                                               LoadingFragment.LoadingFragmentListener,
                                                               ServerListFragment.ServerListFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, LoginFragment.newInstance());
        transaction.commit();
    }


    @Override
    public void onLogin(AuthenticationToken token) {
        changeFragment(LoadingFragment.newInstance(token.getToken()));
    }


    @Override
    public void onLoaded(ArrayList<Server> servers) {
        changeFragment(ServerListFragment.newInstance(servers));
    }

    @Override
    public void onLoadFailed(String message) {

        // TODO failed to get server list and returned back to login fragment.
        // We have to updateLoginFragment with error message to inform user whats happened

        changeFragment(LoginFragment.newInstance());
    }

    @Override
    public void onLogOut() {
        changeFragment(LoginFragment.newInstance());
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);

        // TODO add custom animation for fragment transition
        // transaction.setCustomAnimations()

        transaction.commit();
    }
}
