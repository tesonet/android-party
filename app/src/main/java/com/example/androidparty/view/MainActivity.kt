package com.example.androidparty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidparty.R
import com.example.androidparty.ResponseListener
import com.example.androidparty.model.AuthToken
import com.example.androidparty.model.Server
import com.example.androidparty.networking.AuthClient
import com.example.androidparty.networking.AuthClientFace
import com.example.androidparty.networking.DataClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
