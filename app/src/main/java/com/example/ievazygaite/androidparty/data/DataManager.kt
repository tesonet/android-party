package com.example.ievazygaite.androidparty.data

import android.content.Context
import com.example.ievazygaite.androidparty.R.string.password
import com.example.ievazygaite.androidparty.api.TesoApi
import com.example.ievazygaite.androidparty.data.login.LoginBody
import com.example.ievazygaite.androidparty.data.login.LoginResponse
import com.example.ievazygaite.androidparty.data.server.Server
import io.reactivex.Observable
import io.reactivex.Single

interface DataManager {
    companion object {
        lateinit var prefs: SharedPrefsStorage
        lateinit var api: TesoApi
        
        @Synchronized
        fun initManager(context: Context) {
            prefs = SharedPrefsStorage(context)
            api = TesoApi.create()
        }

        fun auth(email: String, password: String):  Single<LoginResponse> {
            return api.authUser(LoginBody(email, password))
        }

        fun getServers(bearerToken: String): Single<List<Server>> {
            return api.getServers(bearerToken)
        }
    }

}