package com.ac.androidparty.core.sharedprefs

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.ac.androidparty.core.sharedprefs.PreferenceHelper.get
import com.ac.androidparty.core.sharedprefs.PreferenceHelper.set
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class DefaultSharedPreferences(key: String) {
    companion object {
        private lateinit var context: Application
        fun init(application: Application) {
            this.context = application
        }
    }

    protected val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(key, Context.MODE_PRIVATE)
    }

    protected inline fun <reified T> SharedPreferences.delegation(
        preferencesKey: String,
        defaultValue: T
    ): ReadWriteProperty<DefaultSharedPreferences, T> {
        return object : ReadWriteProperty<DefaultSharedPreferences, T> {
            override fun getValue(thisRef: DefaultSharedPreferences, property: KProperty<*>): T =
                this@delegation[preferencesKey, defaultValue]

            override fun setValue(
                thisRef: DefaultSharedPreferences,
                property: KProperty<*>,
                value: T
            ) {
                this@delegation[preferencesKey] = value
            }

        }
    }

    protected fun stringPreferences(preferencesKey: String, defaultValue: String? = null) =
        preferences.delegation(preferencesKey, defaultValue)
}

object PreferenceHelper {
    fun customPrefs(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun SharedPreferences.remove(key: String) = edit { remove(key) }

    operator fun SharedPreferences.set(key: String, value: Any?) = when (value) {
        is String? -> edit { putString(key, value) }
        is Int -> edit { putInt(key, value) }
        is Boolean -> edit { putBoolean(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }

    inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: T? = null
    ) = when (T::class) {
        String::class -> getString(key, defaultValue as? String ?: "") as T
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}