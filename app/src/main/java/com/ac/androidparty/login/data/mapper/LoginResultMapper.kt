package com.ac.androidparty.login.data.mapper

import com.ac.androidparty.login.data.remote.Token
import com.ac.androidparty.login.data.repository.LoginResult

internal object LoginResultMapper {

    operator fun invoke(token: Token): LoginResult = try {
        LoginResult.Success(token)
    } catch (throwable: Throwable) {
        ErrorResultMapper(throwable)
    }
}

internal object ErrorResultMapper {
    operator fun invoke(throwable: Throwable): LoginResult =
        if (throwable.localizedMessage.contains("401")) LoginResult.WrongCredentials
        else LoginResult.Error
}