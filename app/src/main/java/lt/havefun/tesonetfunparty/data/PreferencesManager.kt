package lt.havefun.tesonetfunparty.data

import android.content.SharedPreferences
import lt.havefun.tesonetfunparty.annotations.PreferencesQualifier

data class PreferencesManager(
   @PreferencesQualifier
   private val preferences: SharedPreferences
): IPreferencesManager {
    override fun saveToken(token: String?) {
        preferences.edit()
            .putString("token", token)
            .apply()
    }

    override fun getToken(): String? = preferences.getString("token", null)
}