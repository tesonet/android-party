package com.example.androidparty.ui.login

import android.app.Application
import com.example.androidparty.SharedPreferences
import com.example.androidparty.repository.LoginRepository
import com.example.androidparty.ui.BaseViewModel
import com.example.androidparty.utils.TokenObject
import com.example.androidparty.utils.UserData
import io.reactivex.Observable

class LoginViewModel(application: Application) : BaseViewModel(application) {

    private var loginObservable: Observable<TokenObject> = Observable.empty()
    private val sharedPreferences = SharedPreferences(application.applicationContext)

    fun login(userData: UserData) {
        loginObservable = LoginRepository.getInstance().login(userData)
            .doOnNext { result ->
                sharedPreferences.saveToken("Bearer ${result.token}")
            }
    }

    fun getLoginObservable(): Observable<TokenObject> {
        return loginObservable
    }

}