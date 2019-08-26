package com.example.androidparty

interface ResponseListener<T> {
    fun <T> onResult(data: T)
}