package com.baruckis.androidparty.local

import com.baruckis.androidparty.data.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val preferencesHelper: PreferencesHelper
) : LocalDataSource {

    override fun getToken(): String {
        return preferencesHelper.token
    }

}