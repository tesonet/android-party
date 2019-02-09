package com.example.justinaszableckisand.androidparty.Base

interface BaseView<T> {
    fun setPresenter(presenter: T)

    fun onError(errorResourceId: Int)

    fun onError(errorMessage: String)
}
