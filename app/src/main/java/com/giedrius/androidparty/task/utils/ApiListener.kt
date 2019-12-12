package com.giedrius.androidparty.task.utils

interface ApiListener<T> {
    fun <T> onResult(data: T)
}