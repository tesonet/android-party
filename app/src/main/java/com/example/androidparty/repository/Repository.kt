package com.example.androidparty.repository

import com.example.androidparty.ResponseListener
import com.example.androidparty.model.Server

interface Repository {
    fun getServersList(listener: ResponseListener<List<Server>>)
    fun getToken(username: String, password: String, listener: ResponseListener<String>)
}