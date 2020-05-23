package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.model.LoggedInUserData

interface LocalDataSource {

    fun getToken(): String

    fun getLoggedInUser(): LoggedInUserData?

    fun setLoggedInUser(user: LoggedInUserData?)

}