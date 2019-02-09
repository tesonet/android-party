package com.example.justinaszableckisand.androidparty.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.justinaszableckisand.androidparty.Constants;
import com.example.justinaszableckisand.androidparty.Servers.MainActivity;
import com.example.justinaszableckisand.androidparty.R;
import com.irozon.sneaker.Sneaker;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.prefs.Prefs;
import spencerstudios.com.bungeelib.Bungee;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.etUsername) EditText etUsername;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.btnLogIn) Button btnLogIn;

    LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        LoginPresenter lLoginPresenter = new LoginPresenter(this,this);
        setListeners();
    }

    private void setListeners() {
        btnLogIn.setOnClickListener(v->{
            if(!etUsername.getText().toString().equals("")){
                if(!etPassword.getText().toString().equals("")){
                    mPresenter.logIn(etUsername.getText().toString(),etPassword.getText().toString());
                } else {
                    Sneaker.with(LoginActivity.this).setTitle(getString(R.string.error_empty_password)).sneakError();
                }
            } else {
                Sneaker.with(LoginActivity.this).setTitle(getString(R.string.error_empty_username)).sneakError();
            }
        });
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onError(int errorResourceId) { }

    @Override
    public void onError(@NonNull String errorMessage) {
        Sneaker.with(LoginActivity.this).setTitle(errorMessage).sneakError();
    }

    @Override
    public void onSuccess(@NotNull String token) {
        Log.e("tokenAct", token);
        Prefs.with(this).write(Constants.TOKEN_PREFS,token);
        startActivity(new Intent(this,MainActivity.class));
        Bungee.slideDown(this);
        finish();
    }
}
