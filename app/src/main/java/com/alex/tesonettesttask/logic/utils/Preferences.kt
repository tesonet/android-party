package com.alex.tesonettesttask.logic.utils

import android.content.Context
import android.content.SharedPreferences
import com.alex.tesonettesttask.TesonetApplication

object Preferences {

    private const val APP_SHARED_PREFS = "tesonet.prefs"
    private const val AUTH_TOKEN = "AUTH_TOKEN"
    private val mAppSharedPrefs: SharedPreferences

    init {
        this.mAppSharedPrefs = TesonetApplication.instance.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    fun getAuthToken(): String {
        return mAppSharedPrefs.getString(AUTH_TOKEN, "")
    }

    fun setAuthToken(authToken: String) {
        mAppSharedPrefs.edit().putString(AUTH_TOKEN, authToken).apply()
    }

    fun removeToken() {
        mAppSharedPrefs.edit().remove(AUTH_TOKEN).apply()
    }


}
