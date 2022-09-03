package com.yasserakbbach.androidparty.login.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.yasserakbbach.androidparty.login.domain.model.Session
import com.yasserakbbach.androidparty.login.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okio.IOException

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "android_party_session")

class SessionRepositoryImpl(
    context: Context,
) : SessionRepository {

    private object PreferencesKey {
        val sessionKey = stringPreferencesKey(name = SESSION_KEY)
    }
    private val dataStore = context.dataStore

    override suspend fun saveSession(session: Session) {
        dataStore.edit {
            it[PreferencesKey.sessionKey] = Json.encodeToString(session)
        }
    }

    override suspend fun getSession(): Flow<Session> =
        dataStore.data
            .catch { exception ->
                if(exception is IOException) emit(emptyPreferences())
                else throw exception
            }.map {
                it[PreferencesKey.sessionKey] ?: ""
            }.map {
                it.toProperSession()
            }

    private fun String.toProperSession(): Session =
        try {
            Json.decodeFromString(this)
        } catch (e: Exception) {
            Session(token = null)
        }

    private companion object {
        const val SESSION_KEY = "SESSION_KEY"
    }
}