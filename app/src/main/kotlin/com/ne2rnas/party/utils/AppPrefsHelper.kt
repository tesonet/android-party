package com.ne2rnas.party.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPrefsHelper @Inject constructor(context: Context) : SharedPrefsHelper {
    companion object {
        private const val PREF_NAME = "PARTY"
        private const val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_LOGGED_IN"
        private const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_TOKEN"
    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun isUserLoggedIn() = mPrefs.getBoolean(PREF_KEY_USER_LOGGED_IN_MODE, false)

    override fun setUserLoggedIn(isLoggedIn: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_LOGGED_IN_MODE, isLoggedIn).apply()
    }

    override fun getToken(): String = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "")

    override fun setToken(accessToken: String?) = mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()

}
