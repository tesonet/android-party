package com.codecave.outmatch.shared.server

import com.codecave.outmatch.shared.Settings
import com.google.gson.JsonElement
import okhttp3.*
import com.google.gson.JsonParser
import com.codecave.outmatch.shared.exceptions.HttpCodeException
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.net.ssl.HttpsURLConnection


/**
 * Created by Marius Savickas on 2017-09-01.
 */

open class ApiService(httpClient: OkHttpClient, baseUrl: String) {
    companion object {
        val MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8")
    }

    private var client = httpClient
    private var baseUrl = baseUrl

    open fun executeRequest(request: Request): Single<JsonElement> {
       return Single.fromCallable { client.newCall(request).execute() }
           .subscribeOn(Schedulers.io())
           .map { response ->
               val statusCode = response.code()
               if (statusCode == HttpsURLConnection.HTTP_OK)
                   JsonParser().parse(response.body()?.string())
               else
                   throw HttpCodeException(statusCode)
           }
    }

    open fun post(path: String, body: RequestBody, accessToken: String? = ""): Single<JsonElement> {
        val requestBuilder = Request.Builder()
                .url(baseUrl + path)
                .post(body)

        if (accessToken?.isNotEmpty()!!) {
            requestBuilder.addHeader("Authorization", accessToken)
        }

        return executeRequest(requestBuilder.build())
    }

    open fun get(path: String, accessToken: String? = ""): Single<JsonElement> {
        val requestBuilder = Request.Builder()
                .url(baseUrl + path)

        if (accessToken?.isNotEmpty()!!) {
            requestBuilder.addHeader("Authorization", accessToken)
        }

        return executeRequest(requestBuilder.build())
    }
}
