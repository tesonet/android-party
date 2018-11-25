package com.tesonet.testio.data.mapper

import com.tesonet.testio.data.local.entity.Token
import com.tesonet.testio.data.remote.entity.ApiToken


object TokenMapper {

    public fun map(apiToken: ApiToken) = Token(apiToken.token)

    public fun map(token: Token) = ApiToken(token.token)
}