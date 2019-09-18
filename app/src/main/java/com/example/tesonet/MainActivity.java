package com.example.tesonet;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    public static String token;
    static SharedPreferences sharedPreferences;
    String sharedKey = "token";


    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://playground.tesonet.lt/v1/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient client = retrofit.create(UserClient.class);

    public void logIn(View view){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        Login login = new Login(username, password);
        Call<User> call = client.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    token = response.body().getToken();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(sharedKey, token);
                    editor.apply();
                    callServerList();
                } else {
                    Toast.makeText(MainActivity.this, "Login not correct!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void callServerList(){
        Intent intent = new Intent(this, ServerList.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        sharedPreferences = getSharedPreferences("pref", 0);
        if (sharedPreferences.contains(sharedKey)) {
            token = sharedPreferences.getString(sharedKey, "");
        } else {
            token = "";
        }
        if(token != null && !token.equals("")){
            callServerList();
        }
    }
}
