package com.njakawaii.tesonet.login;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.njakawaii.tesonet.R;

public class BaseActivity extends AppCompatActivity {


    public void checkConnection() {
        if (!isNetworkAvailable(this)){
            showNoInternetModal();
        }
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    public void showNoInternetModal(){
       buildDialog(this).create().show();
    }

       public AlertDialog.Builder buildDialog(Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(getString(R.string.no_internet_title));
        builder.setMessage(getString(R.string.no_internet_description));
        builder.setCancelable(false);
        builder.setPositiveButton("Retry", (dialog, which) -> {
            new Handler().postDelayed(this::checkConnection,1000);
            dialog.dismiss();
        });

        return builder;
    }

}
