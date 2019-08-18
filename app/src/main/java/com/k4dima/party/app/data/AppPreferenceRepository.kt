package com.k4dima.party.app.data

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AppPreferenceRepository
@Inject
constructor(private val preferences: Provider<SharedPreferences>) : PreferenceRepository {
    companion object {
        const val tokenKey = "token"
    }

    override var token: String?
        get() = preferences.get().getString(tokenKey, null)
        set(value) = preferences.get().edit { putString(tokenKey, value) }
}