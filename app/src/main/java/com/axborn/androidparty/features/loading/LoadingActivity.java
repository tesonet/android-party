package com.axborn.androidparty.features.loading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.axborn.androidparty.R;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        String token = getIntent().getStringExtra("TOKEN");
        System.out.println(token);
    }
}
