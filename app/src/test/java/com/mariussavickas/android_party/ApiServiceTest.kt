package com.mariussavickas.android_party

import com.codecave.outmatch.shared.Settings
import com.codecave.outmatch.shared.exceptions.HttpCodeException
import com.codecave.outmatch.shared.server.ApiService
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.reactivex.Single
import okhttp3.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {

    @Mock (answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var mockHttpClient: OkHttpClient

    private val JSON_PARAM = "value"
    private val JSON_PARAM_2 = "value2"
    private val HEADER_AUTH = "Authorization"

    private val JSON_PARAM_VALUE = "ok"
    private val JSON_PARAM_VALUE_2 = "ok2"
    private val HEADER_AUTH_VALUE = "token1"

    private val BASE_URL = "http://localhost"
    private val PATH_URL = "/path"

    @Test
    fun apiService_execute_returnsCorrectValue() {
        val request = Request.Builder()
            .url(BASE_URL)
            .build()

        val response = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("message")
            .body(ResponseBody.create(ApiService.MEDIA_TYPE_JSON, "{ $JSON_PARAM: $JSON_PARAM_VALUE}"))
            .code(200)
            .build()

        `when`(mockHttpClient.newCall(request).execute())
            .thenReturn(response)

        val service = ApiService(mockHttpClient, BASE_URL)
        val jsonElement = service.executeRequest(request).blockingGet()

        val value = jsonElement.asJsonObject.get(JSON_PARAM).asString
        assertEquals(value, JSON_PARAM_VALUE)
    }

    @Test
        fun apiService_execute_throwsException() {
        val request = Request.Builder()
            .url(BASE_URL)
            .build()

        val response = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("message")
            .body(ResponseBody.create(ApiService.MEDIA_TYPE_JSON, "{ $JSON_PARAM: $JSON_PARAM_VALUE}"))
            .code(404)
            .build()

        `when`(mockHttpClient.newCall(request).execute())
            .thenReturn(response)

        val service = ApiService(mockHttpClient, BASE_URL)
        service.executeRequest(request)
            .test()
            .await()
            .assertFailure(HttpCodeException::class.java)
    }

    @Test
    fun apiService_post_returnsCorrectValue() {
        val json = JsonObject ()
        json.addProperty (JSON_PARAM, JSON_PARAM_VALUE)
        json.addProperty (JSON_PARAM_2, JSON_PARAM_VALUE_2)
        val body = RequestBody.create(ApiService.MEDIA_TYPE_JSON, json.toString ())

        val request = Request.Builder()
            .url(BASE_URL + PATH_URL)
            .post(body)
            .build()

        val response = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("message")
            .body(ResponseBody.create(ApiService.MEDIA_TYPE_JSON, json.toString()))
            .code(200)
            .build()

        `when`(mockHttpClient.newCall(any(Request::class.java)).execute())
            .thenReturn(response)

        val service = ApiService(mockHttpClient, BASE_URL)
        val jsonElement = service.post(PATH_URL, body).blockingGet()

        val value = jsonElement.asJsonObject.get(JSON_PARAM).asString
        assertEquals(value, JSON_PARAM_VALUE)

        val value2 = jsonElement.asJsonObject.get(JSON_PARAM_2).asString
        assertEquals(value2, JSON_PARAM_VALUE_2)
    }

    @Test
    fun apiService_get_returnsCorrectValue() {
        val request = Request.Builder()
            .url(BASE_URL + PATH_URL)
            .build()

        val response = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("message")
            .body(ResponseBody.create(ApiService.MEDIA_TYPE_JSON, "{ $JSON_PARAM: $JSON_PARAM_VALUE }"))
            .code(200)
            .build()

        `when`(mockHttpClient.newCall(any(Request::class.java)).execute())
            .thenReturn(response)

        val service = ApiService(mockHttpClient, BASE_URL)
        val jsonElement = service.get(PATH_URL).blockingGet()

        val value = jsonElement.asJsonObject.get(JSON_PARAM).asString
        assertEquals(value, JSON_PARAM_VALUE)
    }
}
