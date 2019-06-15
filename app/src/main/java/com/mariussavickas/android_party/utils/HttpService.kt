package com.codecave.outmatch.shared.server

import com.codecave.outmatch.shared.Settings
import com.google.gson.JsonElement
import okhttp3.*
import com.google.gson.JsonParser
import com.codecave.outmatch.shared.exceptions.HttpCodeException
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection


/**
 * Created by Marius Savickas on 2017-09-01.
 */

object HttpService {
    val MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8")

    private var client = OkHttpClient()
        .newBuilder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .build()

    fun executeRequest(request: Request): Single<JsonElement> {
       return Single.fromCallable { client.newCall(request).execute() }
           .subscribeOn(Schedulers.io())
           .map { response ->
               val statusCode = response.code()
               if (statusCode == HttpsURLConnection.HTTP_OK || statusCode == HttpsURLConnection.HTTP_CREATED)
                   JsonParser().parse(response.body()?.string())
               else
                   throw HttpCodeException(statusCode)
           }
    }

    fun post(path: String, body: RequestBody, accessToken: String = ""): Single<JsonElement> {
        val requestBuilder = Request.Builder()
                .url(Settings.API_ENDPOINT_BASE_V1 + path)
                .post(body)

        if (accessToken.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", accessToken)
        }

        return executeRequest(requestBuilder.build())
    }

    fun get(path: String, accessToken: String? = ""): Single<JsonElement> {
        val requestBuilder = Request.Builder()
                .url(Settings.API_ENDPOINT_BASE_V1 + path)

        if (accessToken?.isNotEmpty()!!) {
            requestBuilder.addHeader("Authorization", accessToken)
        }

        return executeRequest(requestBuilder.build())
    }
}
