package com.baruckis.remote.mapper

import com.baruckis.data.model.TokenData
import com.baruckis.remote.model.ResponseToken
import javax.inject.Inject

class ResponseTokenMapper @Inject constructor() : ApiResponseMapper<ResponseToken, TokenData> {

    override fun mapFrom(remote: ResponseToken): TokenData {
        return TokenData(remote.token)
    }

}