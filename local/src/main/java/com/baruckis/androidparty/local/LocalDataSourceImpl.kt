package com.baruckis.androidparty.local

import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.data.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val sharedPreferenceStorage: PreferenceStorage
) : LocalDataSource {

    override fun getToken(): String {
        return sharedPreferenceStorage.token ?: ""
    }

    override fun getLoggedInUser(): LoggedInUserData? {
        val token = sharedPreferenceStorage.token
        val username = sharedPreferenceStorage.username
        return if (token.isNullOrBlank() || username.isNullOrBlank()) null else
            LoggedInUserData(token, username)
    }

}