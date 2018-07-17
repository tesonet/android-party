package levkovskiy.dev.tesonettest.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils

open class PreferenceHelper {

  companion object {
    var token = "TOKEN"
    /**
     * Helper method to retrieve a String value from [SharedPreferences].
     *
     * @param context a [Context] object.
     * @return The value from shared preferences, or null if the value could not be read.
     */

    fun getStringPreference(
      context: Context,
      key: String
    ): String? {
      var value: String? = null
      val preferences = PreferenceManager.getDefaultSharedPreferences(context)
      if (preferences != null) {
        value = preferences.getString(key, null)
      }
      return value
    }

    /**
     * Helper method to write a String value to [SharedPreferences].
     *
     * @param context a [Context] object.
     * @return true if the new value was successfully written to persistent storage.
     */
    fun setStringPreference(
      context: Context,
      key: String,
      value: String?
    ): Boolean {
      val preferences = PreferenceManager.getDefaultSharedPreferences(context)
      if (preferences != null && !TextUtils.isEmpty(key)) {
        val editor = preferences.edit()
        editor.putString(key, value)
        return editor.commit()
      }
      return false
    }

    fun isNeedToLogin(context: Context) = getStringPreference(context, token) != null
  }
}