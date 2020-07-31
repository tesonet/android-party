package com.demo.androidparty.utils

internal sealed class NetworkResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val errorCode: Int) : NetworkResult<Nothing>()
}