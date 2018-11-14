package place.holder.androidparty.login

import android.content.Context
import place.holder.androidparty.common.api.ApiClient

class ServersProvider(context: Context) {

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
            tag: String) {
        apiClient.requestServers({
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