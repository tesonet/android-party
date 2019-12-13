package com.giedrius.androidparty.task.data

import com.giedrius.androidparty.task.utils.ApiListener
import com.giedrius.androidparty.task.viewmodel.Server
import com.giedrius.androidparty.task.api.login.LoginOutcome

interface Repository {
    fun getServers(apiListener: ApiListener<List<Server>>)
    fun getToken(username: String, password: String, apiListener: ApiListener<LoginOutcome>)
}