package com.tesonet.testio.data.repository

import com.tesonet.testio.base.BaseRepository
import com.tesonet.testio.data.local.dao.CredentialsDao
import com.tesonet.testio.data.local.dao.insertAsync
import com.tesonet.testio.data.local.dao.selectAsync
import com.tesonet.testio.data.local.entity.Credentials
import com.tesonet.testio.util.asynclaucher.AsyncLauncher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CredentialsRepository @Inject constructor(
    private val credentialsDao: CredentialsDao,
    asyncLauncher: AsyncLauncher
): BaseRepository<Credentials?>(asyncLauncher) {

    fun getCredentials() = loadOrGetCached { credentialsDao.selectAsync() }

    fun saveCredentials(credentials: Credentials) {
        setValue(credentials)
        tryRun { credentialsDao.insertAsync(credentials) }
    }

    fun deleteCredentials() {
        unsetValue()
        tryRun { credentialsDao.delete() }
    }
}