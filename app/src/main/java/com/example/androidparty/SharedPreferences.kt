package com.example.androidparty

import android.content.Context

class SharedPreferences(context: Context) {

    private val preferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveToken(token: String) {
        val editor = preferences.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun removeToken() {
        val editor = preferences.edit()
        editor.remove(USER_TOKEN)
        editor.apply()
    }

    fun getToken(): String? {
        return preferences.getString(USER_TOKEN, null)
    }
}