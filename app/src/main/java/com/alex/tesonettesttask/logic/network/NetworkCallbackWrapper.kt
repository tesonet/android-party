package com.alex.tesonettesttask.logic.network

import com.alex.tesonettesttask.R
import com.alex.tesonettesttask.TesonetApplication
import com.alex.tesonettesttask.logic.network.response.ErrorResponse
import io.reactivex.observers.DisposableObserver
import retrofit2.Response

// callback wrapper to handle all the response parsing logic in one place
abstract class NetworkCallbackWrapper<T> : DisposableObserver<Response<T>>() {

    override fun onNext(response: Response<T>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null)
                onSuccess(body)
            else
                onFail(TesonetApplication.instance.applicationContext.getString(R.string.some_error))
        } else {
            if (response.code() == 401)
                onFail(TesonetApplication.instance.applicationContext.getString(R.string.wrong_credentials))
            else {
                onFail(TesonetApplication.gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java).message)
            }
        }
    }

    override fun onError(e: Throwable) {
        onFail(TesonetApplication.instance.applicationContext.getString(R.string.some_error))
    }

    override fun onComplete() {
    }

    protected abstract fun onSuccess(body: T)

    protected abstract fun onFail(message: String)

}