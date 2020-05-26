package com.baruckis.androidparty.presentation

import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.presentation.model.LoginPresentation

object TestDataFactory {

    private const val TOKEN = "f9731b590611a5a9377fbd02f247fcdf"
    private const val USERNAME = "abc"
    private const val PASSWORD = "deg"

    fun createLoggedInUserEntity(): LoggedInUserEntity {
        return LoggedInUserEntity(TOKEN, USERNAME)
    }

    fun createLoginPresentation(): LoginPresentation {
        return LoginPresentation(USERNAME)
    }

    val username = USERNAME
    val password = PASSWORD

    fun createErrorMessage(): String {
        return "Custom error message!"
    }

}