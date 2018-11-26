package com.tesonet.testio.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


abstract class BaseRepository<T> {

    protected var data = MutableLiveData<Resource<T>>()

    protected fun loadOrGetCached(loadAction: suspend () -> T): LiveData<Resource<T>> {
        if (data.value == null || data.value?.status == Resource.Status.ERROR) {
            data.value = Resource.loading()
            GlobalScope.launch { tryLoad(loadAction) }
        }
        return data
    }

    private suspend fun tryLoad(loadAction: suspend () -> T) {
        try {
            val loadedData = loadAction()
            data.postValue(Resource.success(loadedData))
        } catch (ex: Exception) {
            data.postValue(Resource.error(ex))
        }
    }

    protected fun tryRun(loadAction: suspend () -> Unit) {
        GlobalScope.launch {
            try {
                loadAction()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    protected fun setValue(value: T) {
        data.value = Resource.success(value)
    }

    protected fun unsetValue() {
        data = MutableLiveData()
    }
}