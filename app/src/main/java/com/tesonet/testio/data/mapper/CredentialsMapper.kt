package com.tesonet.testio.data.mapper

import com.tesonet.testio.data.local.entity.Credentials
import com.tesonet.testio.data.remote.entity.ApiCredentials


object CredentialsMapper {

    public fun map(apiCredentials: ApiCredentials)
            = Credentials(apiCredentials.username, apiCredentials.password)

    public fun map(credentials: Credentials) = ApiCredentials(credentials.username, credentials.password)
}