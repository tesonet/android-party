package com.tesonet.testio.utils

import android.content.Context
import com.tesonet.testio.ui.MainActivity
import com.tesonet.testio.ui.MainActivity.Companion

class LoginHelper(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(MainActivity.LOG_IN_STATE, Context.MODE_PRIVATE)

    fun logIn() = sharedPreferences.edit().putBoolean(Companion.LOG_IN_STATE, true).apply()
    fun logOut() = sharedPreferences.edit().putBoolean(Companion.LOG_IN_STATE, false).apply()
    fun isLoggedIn() = sharedPreferences.getBoolean(Companion.LOG_IN_STATE, false)
}