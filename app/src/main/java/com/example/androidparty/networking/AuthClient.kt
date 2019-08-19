package com.example.androidparty.networking

import com.example.androidparty.ResponseListener
import com.example.androidparty.model.AuthToken
import com.example.androidparty.model.UserRequest
import java.util.*

interface AuthClient {
    fun sendCredentials(userRequest: UserRequest, listener: ResponseListener<AuthToken>)
}