package com.k4dima.androidparty.features.login.presentation

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.k4dima.androidparty.features.app.domain.UseCase
import com.k4dima.androidparty.features.login.ui.di.LoginScope
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@LoginScope
class LoginViewModel
@Inject
constructor(private val useCase: UseCase<Array<String>, String>) : ViewModel() {
    val error = MutableLiveData<String>()
    val token = MutableLiveData<String>()
    private var disposables = CompositeDisposable()
    var username = ""
    var password = ""
    fun login(@Suppress("UNUSED_PARAMETER") button: View) {
        useCase.data(arrayOf(username, password))
                .subscribe({ token.postValue(it) }, {
                    it.printStackTrace()
                    error.postValue(it.localizedMessage)
                })
                .addTo(disposables)
    }

    override fun onCleared() = disposables.dispose()
}