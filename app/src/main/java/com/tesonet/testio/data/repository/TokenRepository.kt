package com.tesonet.testio.data.repository

import com.tesonet.testio.base.BaseRepository
import com.tesonet.testio.data.local.dao.TokenDao
import com.tesonet.testio.data.local.dao.insertAsync
import com.tesonet.testio.data.local.dao.selectAsync
import com.tesonet.testio.data.local.entity.Credentials
import com.tesonet.testio.data.local.entity.Token
import com.tesonet.testio.data.mapper.CredentialsMapper
import com.tesonet.testio.data.mapper.TokenMapper
import com.tesonet.testio.data.remote.PlaygroundApi
import com.tesonet.testio.data.remote.entity.ApiToken
import com.tesonet.testio.util.NetworkAvailability
import retrofit2.HttpException
import javax.inject.Inject


class TokenRepository @Inject constructor(
    private val api: PlaygroundApi,
    private val tokenDao: TokenDao,
    private val networkAvailability: NetworkAvailability
): BaseRepository<Token?>() {

    fun getToken(credentials: Credentials) = loadOrGetCached {
        val localToken = tokenDao.selectAsync()

        // Return locally saved token if it's still valid
        if (localToken != null && !localToken.isExpired()) {
            localToken
        } else if (networkAvailability.isNetworkAvailable()) {
            getTokenFromRemote(credentials)
        } else localToken ?: throw InternetRequiredException()
        // Internet is required for the first login to fetch the servers' list
    }

    private suspend fun getTokenFromRemote(credentials: Credentials): Token {
        val apiToken = login(credentials)
        val token = TokenMapper.map(apiToken)
        tokenDao.insertAsync(token)
        return token
    }

    private suspend fun login(credentials: Credentials): ApiToken {
        try {
            return api.login(CredentialsMapper.map(credentials)).await()
        } catch (ex: HttpException) {
            if (ex.code() == 401) {
                throw BadCredentialsException()
            }
            throw ex
        }
    }

    class BadCredentialsException: Exception()
    class InternetRequiredException: Exception()
}