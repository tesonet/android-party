package place.holder.androidparty.common.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import place.holder.androidparty.AppController
import place.holder.androidparty.common.api.model.Server

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
                { onError(it.networkResponse?.statusCode ?: 500) }
        ).setTag(tag))
    }

    fun requestServers(onSuccess: (List<Server>) -> Unit, onError: (status: Int) -> Unit, tag: String) {
        queue.add(object : StringRequest(Request.Method.GET, "http://playground.tesonet.lt/v1/servers",
                { response ->
                    try {
                        val jsonArray = JSONArray(response)
                        val servers = ArrayList<Server>(jsonArray.length())
                        for (i in 0 until jsonArray.length()) {
                            servers.add(jsonArray.getJSONObject(i).let {
                                Server(it.optString("name").orEmpty(), it.optInt("distance"))
                            })
                        }
                        onSuccess(servers)
                    } catch (ex: JSONException) {
                        onError(500)
                    }
                }, {
            onError(it.networkResponse?.statusCode ?: 500)
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                return mutableMapOf(
                        "Content-Type" to "application/json",
                        "Authorization" to "Bearer ${AppController.instance.token}")
            }
        }.setTag(tag))
    }

    fun cancel(tag: String) = queue.cancelAll(tag)
}