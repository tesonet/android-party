package com.ac.androidparty.core.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

inline fun Interceptor.Chain.proceedWith(builderFunction: Request.Builder.() -> Any?): Response {
    val builder = request().newBuilder()
    builder.builderFunction()
    return proceed(builder.build())
}

fun OkHttpClient.Builder.addInterceptors(interceptors: Set<Interceptor>) = apply {
    interceptors().addAll(interceptors)
}