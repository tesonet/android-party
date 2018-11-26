package com.tesonet.testio.base


class Resource<T> private constructor(
    val status: Status,
    val data: T? = null,
    val exception: Throwable? = null
) {

    enum class Status {
        SUCCESS, ERROR
    }

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(Status.ERROR, exception = throwable)
        }
    }
}