package lt.topocentras.android

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.lukas.tesonettest.model.Token
import lt.topocentras.android.api.Api


/**
 * Created by marius on 17.2.10.
 */
object Prefs {
	private val AUTHORIZATION = "authorization"
	lateinit var context: Context

	val preferences: SharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(context)
	}

	var authorization: Token?
		get() {
			preferences.getString(AUTHORIZATION, null)?.let {
				return Api.gson.fromJson(it, Token::class.java)
			} ?: return null
		}
		set(value) {
			val json = value?.let {
				Api.gson.toJson(it)
			}
			preferences.edit().putString(AUTHORIZATION, json).apply()

		}
}