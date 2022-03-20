package com.example.domain_login.data.datasource.local

import com.example.domain_login.data.datastore.LocalSharedPreference
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStore: LocalSharedPreference
) : LocalDataSource {
    override fun saveToken(token: String) {
        dataStore.saveToken(token = token)
    }
}