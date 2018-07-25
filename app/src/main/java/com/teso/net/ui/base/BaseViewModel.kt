package com.teso.net.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


abstract class BaseViewModel : ViewModel() {

    protected val disposal: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposal.clear()
        Timber.d("View model was cleared ${this.javaClass.simpleName}")
    }
}