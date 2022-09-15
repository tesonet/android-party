package com.ac.androidparty

import android.app.Application
import com.ac.androidparty.core.sharedprefs.DefaultSharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidPartyAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DefaultSharedPreferences.init(this)
    }
}