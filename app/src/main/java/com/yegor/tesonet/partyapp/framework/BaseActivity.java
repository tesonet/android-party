package com.yegor.tesonet.partyapp.framework;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yegor.tesonet.partyapp.ui.LoadingDialog;
import com.yegor.tesonet.partyapp.events.NetworkErrorEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Unbinder;

/**
 * Base activity for all activities in app
 */
public class BaseActivity extends AppCompatActivity {

    private static final String DIALOG_TAG = "DIALOG_TAG";

    protected Unbinder mUnBinder;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    protected void showWaitDialog(String text) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        LoadingDialog newFragment = LoadingDialog.newInstance(text);
        newFragment.show(ft, DIALOG_TAG);
    }

    protected void hideWaitDialog() {
        FragmentManager manager = getFragmentManager();
        LoadingDialog dialog = (LoadingDialog) manager.findFragmentByTag(DIALOG_TAG);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Subscribe
    @SuppressWarnings("unused")
    void onLoginFailed(NetworkErrorEvent errorEvent){
        hideWaitDialog();
        Toast.makeText(this, errorEvent.getErrorMessage(), Toast.LENGTH_LONG).show();
    }
}
