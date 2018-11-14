package place.holder.androidparty.common.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class ApiClient(context: Context) {

    private val queue = Volley.newRequestQueue(context)

    fun requestToken(
        username: String,
        password: String,
        onSuccess: (token: String) -> Unit,
        onError: (status: Int) -> Unit,
        tag: String
    ) {
        val jsonObject = JSONObject().put("username", username).put("password", password)
        queue.add(JsonObjectRequest(
            Request.Method.POST,
            "http://playground.tesonet.lt/v1/tokens",
            jsonObject,
            { onSuccess(it.optString("token").orEmpty()) },
            { onError(it.networkResponse.statusCode) }
        ).setTag(tag))
    }

    fun cancel(tag: String) = queue.cancelAll(tag)
}