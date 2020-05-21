package com.baruckis.local

import com.baruckis.data.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val preferencesHelper: PreferencesHelper
) : LocalDataSource {

    override fun getToken(): String {
        return preferencesHelper.token
    }

}