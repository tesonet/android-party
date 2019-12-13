package com.giedrius.androidparty.task.api.login

import com.giedrius.androidparty.task.viewmodel.Token
import com.giedrius.androidparty.task.viewmodel.LoginBody
import com.giedrius.androidparty.task.utils.ApiListener

interface LoginClient {
    fun login(body: LoginBody, apiListener: ApiListener<Token>)
}