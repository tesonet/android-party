package core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

@ActivityRetainedScoped
class TokenLocalDataSource @Inject constructor(private val datastoreProvider: Provider<DataStore<Preferences>>) {
    private suspend fun datastore() = withContext(Dispatchers.IO) { datastoreProvider.get() }

    @OptIn(FlowPreview::class)
    internal fun tokens() =
        flow { emit(datastore()) }
            .flatMapConcat { it.data }
            .map { it[tokenKey] }
            .flowOn(Dispatchers.IO)

    internal suspend fun getToken() = withContext(Dispatchers.IO) { tokens().first() }

    internal suspend fun setToken(token: String?) {
        withContext(Dispatchers.IO) {
            datastore().edit {
                if (token == null) it.remove(tokenKey)
                else it[tokenKey] = token
            }
        }
    }

    companion object {
        private val tokenKey = stringPreferencesKey("token")
    }
}