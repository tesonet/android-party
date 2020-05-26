package com.baruckis.androidparty.ui

import com.baruckis.androidparty.presentation.model.LoginPresentation

object TestDataFactory {

    private const val USERNAME = "abc"

    fun createLoginPresentation(): LoginPresentation {
        return LoginPresentation(USERNAME)
    }

}