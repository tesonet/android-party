package com.thescriptan.tesonetparty.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("settings")


@Singleton
class TesoDataStore @Inject constructor(@ApplicationContext context: Context) {
    private val dataStore = context.dataStore

    companion object {
        const val TOKEN = "token"
    }

    private object PreferenceKeys {
        val TOKEN = stringPreferencesKey(TesoDataStore.TOKEN)
    }

    suspend fun setToken(token: String) {
        dataStore.edit { store ->
            store[PreferenceKeys.TOKEN] = token
        }
    }

    val token: Flow<String> = dataStore.data.map { store ->
        store[PreferenceKeys.TOKEN] ?: ""
    }
}