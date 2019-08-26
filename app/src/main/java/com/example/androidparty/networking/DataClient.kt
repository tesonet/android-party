package com.example.androidparty.networking

import com.example.androidparty.ResponseListener
import com.example.androidparty.model.AuthToken
import com.example.androidparty.model.Server

interface DataClient {
    fun getServersList(listener: ResponseListener<List<Server>>)
}