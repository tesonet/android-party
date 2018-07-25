package com.teso.net.data_flow.interactions

import android.content.Context
import android.text.TextUtils
import com.teso.net.data_flow.IUserStorage
import com.teso.net.data_flow.network.Api
import com.teso.net.data_flow.network.NetworkUnreachableExeption
import com.teso.net.data_flow.network.api_models.TokenAnswer
import com.teso.net.utils.NetworkUtils
import io.reactivex.Observable

class TokenInteractor constructor(private val api: Api, private val pref: IUserStorage, private val context: Context) : ITokenInteractor {

    override fun getTokenFromServer(userName: String, password: String): Observable<TokenAnswer> {

        if (!NetworkUtils.isNetworkAvailable(context)) return Observable.error(NetworkUnreachableExeption())

        return if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Observable.error(IllegalStateException("Some fields is empty"))
        } else {
            api.getToken(userName, password)
                    .doOnNext { token ->
                        pref.setToken(token.token ?: "")
                        pref.setUserName(userName)
                        pref.setPassword(password)
                    }
        }
    }
}