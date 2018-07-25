package com.teso.net.data_flow.interactions

import com.teso.net.data_flow.network.api_models.TokenAnswer
import io.reactivex.Observable

interface ITokenInteractor {
    fun getTokenFromServer(userName: String, password: String): Observable<TokenAnswer>
}