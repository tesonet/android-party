package place.holder.androidparty

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class AppController : Application() {

    private lateinit var internalUsePreferences: SharedPreferences
    var token: String?
        get() = internalUsePreferences.getString(TOKEN_KEY, null)
        set(value) {
            internalUsePreferences.edit().putString(TOKEN_KEY, value).apply()
        }
    var username: String?
        get() = internalUsePreferences.getString(USERNAME_KEY, null)
        set(value) {
            internalUsePreferences.edit().putString(USERNAME_KEY, value).apply()
        }

    override fun onCreate() {
        super.onCreate()
        internalUsePreferences = this.getSharedPreferences(INTERNAL_USE_PREFERENCES, Context.MODE_PRIVATE)
        appInstance = this
    }

    companion object {
        private const val INTERNAL_USE_PREFERENCES = "Internal use preferences"
        private const val TOKEN_KEY = "Token Key"
        private const val USERNAME_KEY = "Username Key"

        private lateinit var appInstance: AppController
        val instance: AppController
            get() = appInstance
    }
}