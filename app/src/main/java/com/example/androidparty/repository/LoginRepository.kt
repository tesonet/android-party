package com.example.androidparty.repository

import android.annotation.SuppressLint
import com.example.androidparty.network.Network
import com.example.androidparty.utils.TokenObject
import com.example.androidparty.utils.UserData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginRepository {

    companion object {
        fun getInstance() = LoginRepository()
    }

    @SuppressLint("CheckResult")
    fun login(userData: UserData): Observable<TokenObject> {
        return Network().getApi().authenticate(userData)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}