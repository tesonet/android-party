package com.simplekjl.domain.utils

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val throwable: Throwable?) : Result<Nothing>()
}

fun <T> Result<T>.getOrNull(): T? = (this as? Result.Success<T>)?.data
