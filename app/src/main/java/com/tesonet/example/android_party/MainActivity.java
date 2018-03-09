package com.tesonet.example.android_party;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.tesonet.example.android_party.model.ExListItem;
import com.tesonet.example.android_party.model.ExListResponse;
import com.tesonet.example.android_party.model.TokenResponse;
import com.tesonet.example.android_party.utils.Preferences;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ExListItem> exList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        changeFragment(new LoginFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Preferences.getTokenValue(this) != null && !Preferences.getTokenValue(this).isEmpty()) {
            exList = Preferences.getExList(this);
            if (exList != null) {
                changeFragment(new ExListFragment());
            } else {
                changeFragment(new LoginFragment());
            }
        }
    }

    private void changeFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.coordinator, fragment).commit();
        } else {
            Log.e("MainA", "Error while inflating necessary fragment");
        }
    }

    private void getList() {
        ListTask task = new ListTask(this);
        task.execute();
    }

    public void handleToken(TokenResponse result) {
        if (result.getToken() != null) {
            Preferences.storeTokenValue(this, result.getToken());
            if (Preferences.getExList(this) != null && Preferences.getExList(this).size() > 0) {
                exList = Preferences.getExList(this);
                changeFragment(new ExListFragment());
            } else {
                getList();
            }
        } else {
            changeFragment(new LoginFragment());
            new AlertDialog.Builder(this)
                    .setTitle("Connection problem")
                    .setMessage("Problem occurred while connecting to server")
                    .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        }
    }

    public void handleExList(ExListResponse result) {
        if (result != null) {
            exList = result.getList();
            Preferences.storeExList(this, result.getList());
            changeFragment(new ExListFragment());
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Connection problem")
                    .setMessage("Problem occurred while getting data from server")
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            getList();
                            changeFragment(new LoadingFragment());
                        }
                    })
                    .show();
        }
    }

    public ArrayList<ExListItem> getExList() {
        return exList;
    }

    public void showLoadingFrag() {
        changeFragment(new LoadingFragment());
    }

    public void showLoginFrag() {
        changeFragment(new LoginFragment());
    }
}
