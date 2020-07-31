package com.demo.androidparty.storage.preferences

import android.content.SharedPreferences

class AppPreferences(private val preferences: SharedPreferences) {

    internal fun saveToken(token: String) {
        preferences
            .edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }

    internal fun getToken(): String? = preferences.getString(KEY_TOKEN, null)

    internal fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {
        internal const val NAME = "AndroidPartyPrefs"
        private const val KEY_TOKEN = "appToken"
    }
}