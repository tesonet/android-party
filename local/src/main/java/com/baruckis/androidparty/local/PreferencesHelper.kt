package com.baruckis.androidparty.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * General Preferences Helper class, used for storing preference values using the Preference API.
 */
@Singleton
class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.baruckis.androidparty.preferences"
        private const val PREF_TOKEN_API = "token_api"
    }

    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    val token: String
        get() = preferences.getString(PREF_TOKEN_API, "") ?: ""

}