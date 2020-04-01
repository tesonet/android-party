package com.assignment.android_party2.preferences

import android.content.Context
import android.content.SharedPreferences
import com.assignment.android_party2.R
import com.assignment.android_party2.utils.Coroutines

class PreferencesProvider(context: Context) {

    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveLoginToken(token: String) {
        Coroutines.io {
            val editor = prefs.edit()
            editor.putString(USER_TOKEN, token)
            editor.apply()
        }
    }

    fun fetchLoginToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}