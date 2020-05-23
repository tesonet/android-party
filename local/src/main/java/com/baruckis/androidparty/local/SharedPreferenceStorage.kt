package com.baruckis.androidparty.local

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * [PreferenceStorage] implementation backed by [android.content.SharedPreferences].
 */
@Singleton
class SharedPreferenceStorage @Inject constructor(context: Context) : PreferenceStorage {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.baruckis.androidparty.preferences"
        private const val PREF_TOKEN = "token"
        private const val PREF_USERNAME = "username"
    }

    private val preferences =
        context.applicationContext.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    override var token by StringPreference(preferences, PREF_TOKEN, null)

    override var username by StringPreference(preferences, PREF_USERNAME, null)
}

/*
* Delegated Properties
* Learn more: https://proandroiddev.com/kotlin-delegates-in-android-1ab0a715762d
* */
class StringPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.edit().putString(name, value).apply()
    }
}