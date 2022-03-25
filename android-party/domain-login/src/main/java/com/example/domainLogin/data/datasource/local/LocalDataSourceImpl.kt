package com.example.domainLogin.data.datasource.local

import com.example.domainLogin.data.datastore.LocalSharedPreference
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStore: LocalSharedPreference
) : LocalDataSource {
    override fun saveToken(token: String) {
        dataStore.saveToken(token = token)
    }
}
