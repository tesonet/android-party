package com.baruckis.androidparty.remote

import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.remote.RemoteDataSourceImpl
import com.baruckis.androidparty.remote.api.TesonetApiService
import com.baruckis.androidparty.remote.mapper.ResponseTokenMapper
import com.baruckis.androidparty.remote.model.ResponseToken
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RemoteDataSourceImplTest {

    private val apiService = mock(TesonetApiService::class.java)
    private val tokenMapper = mock(ResponseTokenMapper::class.java)
    private val remoteDataSource =
        RemoteDataSourceImpl(
            apiService,
            tokenMapper
        )

    private val responseToken =
        TestDataFactory.createResponseToken()
    private val tokenData =
        TestDataFactory.createTokenData()

    private val requestUser =
        TestDataFactory.createRequestBody()

    @Before
    fun setup() {
        stubSendAuthorization(Single.just(responseToken))
        stubMapFrom(responseToken, tokenData)
    }

    @Test
    fun sendAuthorizationCompletes() {
        val testObserver =
            remoteDataSource.sendAuthorization(requestUser.username, requestUser.password).test()
        testObserver.assertComplete()
    }

    @Test
    fun sendAuthorizationReturnsData() {
        val testObserver =
            remoteDataSource.sendAuthorization(requestUser.username, requestUser.password).test()
        testObserver.assertValue(tokenData)
    }

    private fun stubSendAuthorization(
        response: Single<ResponseToken>
    ) {
        Mockito.`when`(apiService.sendAuthorization(requestUser))
            .thenReturn(response)
    }

    private fun stubMapFrom(remoteModel: ResponseToken, dataModel: TokenData) {
        Mockito.`when`(tokenMapper.mapFrom(remoteModel))
            .thenReturn(dataModel)
    }

}