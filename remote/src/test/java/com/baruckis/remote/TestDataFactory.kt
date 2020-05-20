package com.baruckis.remote

import com.baruckis.data.model.TokenData
import com.baruckis.remote.model.RequestUser
import com.baruckis.remote.model.ResponseToken

object TestDataFactory {

    private const val TOKEN = "f9731b590611a5a9377fbd02f247fcdf"
    private const val USERNAME = "abc"
    private const val PASSWORD = "def"

    fun createResponseToken(): ResponseToken {
        return ResponseToken(TOKEN)
    }

    fun createTokenData(): TokenData {
        return TokenData(TOKEN)
    }

    fun createRequestBody(): RequestUser {
        return RequestUser(USERNAME, PASSWORD)
    }

}