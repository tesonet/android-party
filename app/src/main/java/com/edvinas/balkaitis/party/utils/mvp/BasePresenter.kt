package com.edvinas.balkaitis.party.utils.mvp

interface BasePresenter<T> {
    fun takeView(view: T)
    fun dropView()
}
