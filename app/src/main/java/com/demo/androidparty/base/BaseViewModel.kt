package com.demo.androidparty.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.androidparty.utils.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {

    protected fun <T> createMutableLiveData(): LiveData<T> = MutableLiveData<T>()
    protected fun <T> createSingleLiveEvent(): LiveData<T> = SingleLiveEvent<T>()

    protected fun <T> LiveData<T>.setValue(value: T) {
        when (this) {
            is MutableLiveData<T> -> this.value = value
            else -> throw Exception(
                "Not using createMutableLiveData() or createSingleLiveData() to create live data"
            )
        }
    }

    protected fun LiveData<Unit>.call() {
        when (this) {
            is SingleLiveEvent<Unit> -> this.value = null
            else -> throw Exception(
                "Not using createSingleLiveData() to create live data or <Unit> for .call()"
            )
        }
    }
}