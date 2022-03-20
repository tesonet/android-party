package com.example.domain_login.data.datastore

import android.content.SharedPreferences

class LocalSharedPreference(
    private val preferences: SharedPreferences
) {
    companion object {
        private const val TOKEN_KEY = "token"
        const val PREFERENCE_NAME = "Party"
    }

    internal fun saveToken(token: String) {
        preferences
            .edit()
            .putString(TOKEN_KEY, token)
            .apply()
    }

    fun getToken(): String? = preferences.getString(TOKEN_KEY, null)
}