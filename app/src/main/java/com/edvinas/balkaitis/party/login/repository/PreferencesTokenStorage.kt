package com.edvinas.balkaitis.party.login.repository

import android.content.SharedPreferences

class PreferencesTokenStorage(
        private val sharedPreferences: SharedPreferences
) : TokenStorage {
    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun getToken(): String = sharedPreferences.getString(KEY_TOKEN, EMPTY_TOKEN)!!

    companion object {
        private const val KEY_TOKEN = "key.token"
        private const val EMPTY_TOKEN = ""
    }
}
