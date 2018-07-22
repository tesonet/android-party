package com.njakawaii.tesonet.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.njakawaii.tesonet.R;

import io.reactivex.Observable;

public class LoginView implements LoginContract.View {
    private BaseActivity activity;
    private View root;

    private EditText usernameView;
    private EditText passwordView;
    private View progressView;
    private TextView logingBtn;
    private View loginFormView;
    private  ProgressDialog dialog;

    public LoginView(BaseActivity activity, View root) {
        this.activity = activity;
        this.root = root;
        initView();
    }

    private void initView() {
        usernameView = root.findViewById(R.id.username);
        passwordView = root.findViewById(R.id.password);
        logingBtn = root.findViewById(R.id.login_action);
        loginFormView = root.findViewById(R.id.login_form);
        progressView = root.findViewById(R.id.login_progress);
    }

    @Override
    public String getUserName() {
        return usernameView.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return passwordView.getText().toString().trim();
    }

    @Override
    public Observable<Object> loginAction() {
        return RxView.clicks(logingBtn);
    }

    @Override
    public void showPassError(String error) {
        passwordView.setError(error);
    }

    @Override
    public void showUserNameError(String error) {
        usernameView.setError(error);
    }

    @Override
    public void showProgress(boolean show) {
        activity.runOnUiThread(() -> {
        if (show) {
            dialog = ProgressDialog.show(activity, "",
                    "Loading. Please wait...", true);
        }  else {
            dialog.dismiss();
        }
        });
    }

    @Override
    public void showFetchingServers(boolean show) {
                int shortAnimTime = activity.getResources().getInteger(android.R.integer.config_shortAnimTime);
        activity.runOnUiThread(() -> {
        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
        });

    }

}
