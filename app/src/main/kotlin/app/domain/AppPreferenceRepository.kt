package app.domain

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
        const val TOKEN_KEY = "token"
    }

    override var token: String?
        get() = preferences.get().getString(TOKEN_KEY, null)
        set(value) = preferences.get().edit { putString(TOKEN_KEY, value) }
}