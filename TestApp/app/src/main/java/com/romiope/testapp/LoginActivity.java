package com.romiope.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.EditText;
import android.widget.Toast;

import com.romiope.testapp.network.RestClient;
import com.romiope.testapp.network.v1.wrappers.AuthWrapper;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    private final AuthWrapper authEndpoint = RestClient.getInstance().getApi().getAuthApi();

    EditText loginInput;
    EditText passInput;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginInput = findViewById(R.id.et_login);
        passInput = findViewById(R.id.et_password);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        if (BuildConfig.DEBUG) {
            loginInput.setText("tesonet");
            passInput.setText("partyanimal");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.btn_login).setOnClickListener(view -> {
            String login = loginInput.getText().toString();
            String password = passInput.getText().toString();

            subscriptions.add(authEndpoint.performLogin(login, password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(tokenResponse -> {
                                    TokenKeeper.setToken(getApplicationContext(), tokenResponse.body().token);
                                    MainActivity.start(getApplicationContext());
                                },
                            throwable -> Toast.makeText(this, throwable.toString(), Toast.LENGTH_LONG).show()));
        });
    }
}
