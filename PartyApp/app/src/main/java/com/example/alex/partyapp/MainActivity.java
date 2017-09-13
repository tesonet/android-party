package com.example.alex.partyapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.alex.partyapp.di.App;
import com.example.alex.partyapp.loading.LoadingFragment;
import com.example.alex.partyapp.login.LoginFragment;
import com.example.alex.partyapp.servers.ServersFragment;

public class MainActivity extends AppCompatActivity implements NavigationCallback, Runnable {
    Handler handler = new Handler();
    final static int SERVERS_DELAY = 2000;
    private View fullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getAppComponent(this).inject(this);
        setContentView(R.layout.activity_main);
        fullScreen = findViewById(R.id.full_screen);
        if (savedInstanceState == null) {
            showFragment(new ServersFragment(), false);
        }
    }
    
    @SuppressLint("InlinedApi")
    private void hideUI(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        fullScreen.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void navigateToLogin() {
        showFragment(new LoginFragment(), false);
    }

    @Override
    public void navigateToServers() {
        handler.postDelayed(this, SERVERS_DELAY);
    }
    @Override
    public void run() {
        showFragment(new ServersFragment(), true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(this);
    }

    @Override
    public void navigateToLoading() {
        showFragment(new LoadingFragment(), true);
    }

    private <T extends Fragment> void showFragment(T fragment, boolean animate){
        hideUI();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (animate)
            transaction.setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out);
        transaction.replace(R.id.container, fragment).commitAllowingStateLoss();
    }
}
