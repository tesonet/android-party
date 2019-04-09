package com.edvinas.balkaitis.party.utils.mvp

import io.reactivex.disposables.CompositeDisposable

open class ViewPresenter<T> : BasePresenter<T> {
    val subscription = CompositeDisposable()

    private var view: T? = null

    override fun takeView(view: T) {
        this.view = view
    }

    private fun hasView() = view != null

    fun onView(action: T.() -> Unit) {
        if (hasView()) {
            action.invoke(view!!)
        }
    }

    override fun dropView() {
        subscription.dispose()
        view = null
    }
}
