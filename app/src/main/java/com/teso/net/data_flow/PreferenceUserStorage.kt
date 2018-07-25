package com.teso.net.data_flow

import android.content.Context
import android.content.SharedPreferences
import com.teso.net.BuildConfig
import com.securepreferences.SecurePreferences


class PreferenceUserStorage(context: Context) : IUserStorage {

    companion object {
        private const val NAME = "pref"
        private const val SECURE_NAME = "prefs"

        private const val TOKEN_KEY = "teso_token"
        private const val NAME_KEY = "teso_name"
        private const val PASS_KEY = "teso_pass"
    }

    private val preference: SharedPreferences

    init {
        preference = if (BuildConfig.DEBUG) {
            context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }else {
            SecurePreferences(context, "", SECURE_NAME)
        }
    }

    override fun getToken(): String {
        return preference.getString(TOKEN_KEY, "")
    }

    override fun setToken(token: String) {
        preference.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getUserName(): String {
        return preference.getString(NAME_KEY, "")
    }

    override fun setUserName(name: String) {
        preference.edit().putString(NAME_KEY, name).apply()
    }

    override fun getPassword(): String {
        return preference.getString(PASS_KEY, "")
    }

    override fun setPassword(password: String) {
        preference.edit().putString(PASS_KEY, password).apply()
    }
}