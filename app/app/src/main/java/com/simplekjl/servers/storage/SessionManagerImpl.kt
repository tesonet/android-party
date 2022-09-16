package com.simplekjl.servers.storage

import android.content.Context
import android.content.SharedPreferences
import com.simplekjl.domain.repository.SessionManager
import com.simplekjl.servers.R

/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManagerImpl(context: Context) : SessionManager {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    override fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    override fun fetchAuthToken(): String {
        return prefs.getString(USER_TOKEN, "") ?: ""
    }

    override fun deleteAuthToken() {
        prefs.edit().clear().apply()
    }
}