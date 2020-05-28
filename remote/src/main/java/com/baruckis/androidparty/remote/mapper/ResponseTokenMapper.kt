package com.baruckis.androidparty.remote.mapper

import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.remote.model.ResponseToken
import javax.inject.Inject

class ResponseTokenMapper @Inject constructor() :
    ApiResponseMapper<ResponseToken, TokenData> {

    override fun mapFromRemote(remoteModel: ResponseToken): TokenData {
        return TokenData(remoteModel.token)
    }

}