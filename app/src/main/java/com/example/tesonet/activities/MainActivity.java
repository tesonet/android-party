package com.example.tesonet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tesonet.R;
import com.example.tesonet.database.models.Token;
import com.example.tesonet.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    MainViewModel mainViewModel;
    public String tokenString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.getBooleanExtra("finished", false)) {
            mainViewModel.deleteToken();
        }
    }

    private void init() {
        //Input
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);

        //ViewModel
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getToken().observe(this, new Observer<Token>() {
            @Override
            public void onChanged(Token token) {
                tokenString = token.getToken();
                if (mainViewModel.isLoginSucceded()) {
                    changeActivity();
                }
            }
        });
    }

    public void logIn(View view) {
        mainViewModel.sendLoginRequest(username.getText().toString(), password.getText().toString());
    }

    public void changeActivity() {
        Intent intent = new Intent(MainActivity.this, ServerListActivity.class);
        intent.putExtra("token", tokenString);
        startActivity(intent);
    }
}
