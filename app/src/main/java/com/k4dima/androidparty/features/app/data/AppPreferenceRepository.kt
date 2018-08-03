package com.k4dima.androidparty.features.app.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferenceRepository
@Inject
constructor(context: Context) : PreferenceRepository {
    companion object {
        const val tokenKey = "token"
    }

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    override var token: String
        get() = preferences.getString(tokenKey, "")
        set(value) = preferences.edit { putString(tokenKey, value) }
}