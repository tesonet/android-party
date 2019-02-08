package com.example.justinaszableckisand.androidparty

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.justinaszableckisand.androidparty.Login.LoginActivity
import com.example.justinaszableckisand.androidparty.Servers.MainActivity

import es.dmoral.prefs.Prefs
import spencerstudios.com.bungeelib.Bungee

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Prefs.with(this).read(Constants.TOKEN_PREFS, "") == "") {
            startActivity(Intent(this, LoginActivity::class.java))
        } else startActivity(Intent(this, MainActivity::class.java))

        Bungee.slideDown(this)
        finish()
    }
}
