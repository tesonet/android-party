package com.demo.androidparty.ui.main

import com.demo.androidparty.storage.preferences.AppPreferences

class MainModel(private val preferences: AppPreferences) {

    internal fun getToken(): String? = preferences.getToken()
}