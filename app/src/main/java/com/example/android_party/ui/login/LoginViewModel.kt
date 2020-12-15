package com.example.android_party.ui.login

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.android_party.BaseViewModel
import com.example.android_party.api.TesonetApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    private val TAG = LoginViewModel::class.java.simpleName

    private lateinit var subscription: Disposable

    @Inject
    lateinit var tesonetApi: TesonetApi

    @Inject
    lateinit var loginRepository: LoginRepository

    var username = ObservableField<String>()
    var password = ObservableField<String>()

    val loginSuccess = MutableLiveData<Boolean>()
    val loginError = MutableLiveData<String>()

    init {
        isUserLoggedIn()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun onLoginButtonClicked(view: View) {
        performLogin(username.get().toString(), password.get().toString())
    }

    fun isUserLoggedIn() {
        subscription = Observable.fromCallable { loginRepository.getToken().isNotEmpty() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { hasToken -> loginSuccess.postValue(hasToken) },
                { e -> Log.e(TAG, e.toString()) })
    }

    private fun performLogin(username: String, password: String) {
        subscription = Observable.fromCallable { loginRepository.getToken() }.concatMap { dbToken ->
            if (dbToken.isEmpty()) {
                loginRepository.getTokenFromApi(tesonetApi, username, password)
                    .concatMap { apiToken ->
                        loginRepository.storeToken(apiToken)
                        Observable.just(apiToken.token)
                    }
            } else
                Observable.just(dbToken)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onRetrieveTokenSuccess(result.isNotEmpty()) },
                { result -> onRetrieveTokenError(result) })
    }

    private fun onRetrieveTokenSuccess(hasToken: Boolean) {
        loginSuccess.postValue(hasToken)
    }

    private fun onRetrieveTokenError(response: Throwable) {
        Log.e(TAG, response.message.toString())
        loginError.postValue(response.localizedMessage)
    }
}
