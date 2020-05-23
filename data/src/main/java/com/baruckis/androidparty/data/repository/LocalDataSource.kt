package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.model.LoggedInUserData

interface LocalDataSource {

    fun getLoggedInUser(): LoggedInUserData?

    fun getToken(): String

}