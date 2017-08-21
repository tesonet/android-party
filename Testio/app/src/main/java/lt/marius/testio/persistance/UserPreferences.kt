package lt.marius.testio.persistance

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by marius on 17.8.21.
 */
class UserPreferences(private val context: Context) {
	companion object {
		val KEY_AUTHORIZATION = "authorization"
	}

	var authorization: String?
		get() = sharedPreferences.getString(KEY_AUTHORIZATION, null)
		set(value) {
			sharedPreferences
					.edit()
					.putString(KEY_AUTHORIZATION, value)
					.apply()
		}

	private val sharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(context)
	}
}