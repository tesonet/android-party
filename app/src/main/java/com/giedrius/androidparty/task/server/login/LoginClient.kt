package com.giedrius.androidparty.task.server.login

import com.giedrius.androidparty.task.model.Token
import com.giedrius.androidparty.task.model.LoginBody
import com.giedrius.androidparty.task.utils.ApiListener

interface LoginClient {
    fun login(body: LoginBody, apiListener: ApiListener<Token>)
}