package com.baruckis.androidparty.domain

import com.baruckis.androidparty.domain.entity.LoggedInUserEntity

object TestDataFactory {

    private const val TOKEN = "f9731b590611a5a9377fbd02f247fcdf"
    private const val USERNAME = "abc"
    private const val PASSWORD = "def"

    fun createLoggedInUserEntity(): LoggedInUserEntity {
        return LoggedInUserEntity(TOKEN, USERNAME)
    }

    val username = USERNAME
    val password = PASSWORD

}