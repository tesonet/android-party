package com.baruckis.androidparty.presentation.state

/**
 * Status of a resource that is provided to the UI.
 *
 * Use LiveData<Resource<T>> to pass back the latest data to the UI with its fetch status.
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}