package com.czech.androidparty.utils

import com.czech.androidparty.models.LoginError

data class DataState<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false
) {
    companion object {

        fun <T> error(
            message: LoginError
        ): DataState<T> {
            return DataState(
                message = message.message
            )
        }

        fun <T> loading(
            isLoading: Boolean
        ): DataState<T> {
            return DataState(
                isLoading = isLoading
            )
        }

        fun <T> data(
            data: T? = null
        ): DataState<T> {
            return DataState(
                data = data
            )
        }

        fun <T> loading() = DataState<T>(
            isLoading = true
        )
    }
}