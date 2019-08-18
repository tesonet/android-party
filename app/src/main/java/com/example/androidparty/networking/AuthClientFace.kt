package com.example.androidparty.networking

import com.example.androidparty.ResponseListener
import com.example.androidparty.model.AuthToken
import java.util.*

interface AuthClientFace {
    fun sendCredentials(username: String, password: String, listener: ResponseListener<AuthToken>)
}