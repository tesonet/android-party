package com.edvinas.balkaitis.party.utils.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class ViewPresenter<T> : BasePresenter<T> {
    private val disposables = CompositeDisposable()

    private var view: T? = null

    override fun takeView(view: T) {
        this.view = view
    }

    override fun dropView() {
        disposables.dispose()
        view = null
    }
}
