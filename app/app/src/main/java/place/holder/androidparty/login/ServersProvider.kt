package place.holder.androidparty.login

import android.content.Context
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import place.holder.androidparty.common.api.ApiClient
import place.holder.androidparty.common.database.DatabaseOpenHelper
import place.holder.androidparty.common.database.database

class ServersProvider(private val context: Context) {

    private val apiClient = ApiClient(context)

    fun login(
            username: String, password: String,
            onSuccess: (token: String) -> Unit,
            onAuthenticationError: () -> Unit,
            onServerError: () -> Unit,
            tag: String
    ) {
        apiClient.requestToken(username, password, onSuccess, { status ->
            if (status / 100 == 5) {
                onServerError()
            } else {
                onAuthenticationError()
            }
        }, tag)
    }

    fun requestServers(
            onSuccess: () -> Unit,
            onAuthenticationError: () -> Unit,
            onServerError: () -> Unit,
            tag: String
    ) {
        apiClient.requestServers({ servers ->
            context.database.use {
                delete(DatabaseOpenHelper.TABLE_SERVER)
                servers.forEach { server ->
                    insert(DatabaseOpenHelper.TABLE_SERVER,
                            DatabaseOpenHelper.SERVER_NAME to server.name,
                            DatabaseOpenHelper.SERVER_DISTANCE to server.distance)
                }
            }
            onSuccess()
        }, { status ->
            if (status / 100 == 5) {
                onServerError()
            } else {
                onAuthenticationError()
            }
        }, tag)
    }

    fun cancelRequest(tag: String) = apiClient.cancel(tag)
}