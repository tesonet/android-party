package com.example.tesonet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerList extends AppCompatActivity {

    ImageView logoImageView;
    ImageView imageView;
    ImageView logOutImageView;
    ImageView loaderImageView;
    TextView loaderTextView;
    TextView serverTextView;
    TextView distanceTextView;
    ProgressBar progressBar;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;
    int i = 0;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<Server> servers;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://playground.tesonet.lt/v1/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient client = retrofit.create(UserClient.class);

    public void logout(View view){
        SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
        editor.remove("token");
        editor.apply();
        finish();
    }

    public void setUpRecyclerView(List<Server> servers){

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomRecyclerAdapter(this, servers);

        recyclerView.setAdapter(adapter);
    }

    public <T> void setList(String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }

    public static void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void callServer(){
        sharedPreferences = this.getSharedPreferences("Servers", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        final List<Server> arrayItems;
        String serializedObject = sharedPreferences.getString("servers", null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Server>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);
            setUpRecyclerView(arrayItems);
        } else {
            Call<List<Server>> callServer = client.getServer("Bearer " + MainActivity.token);

            callServer.enqueue(new Callback<List<Server>>() {
                @Override
                public void onResponse(Call<List<Server>> call, Response<List<Server>> response) {
                    if(response.isSuccessful()){
                        servers = response.body();
                        setList("servers", servers);
                        setUpRecyclerView(servers);
                    } else {
                        Toast.makeText(ServerList.this, "Token is not correct!", Toast.LENGTH_SHORT).show();
                        MainActivity.token = "";
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<List<Server>> call, Throwable t) {
                    Toast.makeText(ServerList.this, "Error", Toast.LENGTH_SHORT).show();
                    MainActivity.token = "";
                    finish();
                }
            });
        }
    }

    public void loadingScreen(){


        logoImageView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        logOutImageView.setVisibility(View.INVISIBLE);
        serverTextView.setVisibility(View.INVISIBLE);
        distanceTextView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

        loaderImageView.setVisibility(View.VISIBLE);
        loaderTextView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void serverListScreen(){

        logoImageView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        logOutImageView.setVisibility(View.VISIBLE);
        serverTextView.setVisibility(View.VISIBLE);
        distanceTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

        loaderImageView.setVisibility(View.INVISIBLE);
        loaderTextView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void animate(){
        new CountDownTimer(3000, 400){
            public void onTick(long millisecondsUntilDone){
                switch(i){
                    case 0:
                        loaderTextView.setText("Fetching the list");
                        i = 1;
                        break;
                    case 1:
                        loaderTextView.setText("Fetching the list.");
                        i = 2;
                        break;
                    case 2:
                        loaderTextView.setText("Fetching the list..");
                        i = 3;
                        break;
                    case 3:
                        loaderTextView.setText("Fetching the list...");
                        i = 0;
                        break;
                }
            }
            public void onFinish(){
                serverListScreen();
            }

        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);
        logoImageView = (ImageView) findViewById(R.id.logoImageView);
        imageView = (ImageView) findViewById(R.id.imageView);
        logOutImageView = (ImageView) findViewById(R.id.logoutImageView);
        loaderImageView = (ImageView) findViewById(R.id.loaderImageView);
        loaderTextView = (TextView) findViewById(R.id.loaderTextView);
        serverTextView = (TextView) findViewById(R.id.serverTextView);
        distanceTextView = (TextView) findViewById(R.id.distanceTextView);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        progressBar = (ProgressBar) findViewById(R.id.pBar);




        loadingScreen();
        callServer();
        animate();
    }
}
