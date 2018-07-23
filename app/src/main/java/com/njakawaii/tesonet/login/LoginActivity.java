package com.njakawaii.tesonet.login;

import android.os.Bundle;
import android.view.ViewGroup;

import com.njakawaii.tesonet.R;

public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) (findViewById(android.R.id.content))).getChildAt(0);
        LoginView loginView = new LoginView(this, viewGroup);
        LoginPresenter loginPresenter = new LoginPresenter(this, loginView);
        getLifecycle().addObserver(loginPresenter);
    }

}

