package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.TestDataFactory
import com.baruckis.androidparty.data.mapper.TokenMapper
import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.data.repository.LocalDataSource
import com.baruckis.androidparty.data.repository.MainRepositoryImpl
import com.baruckis.androidparty.data.repository.RemoteDataSource
import com.baruckis.androidparty.domain.entity.TokenEntity
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.mock

class MainRepositoryImplTest {

    private val tokenMapper = mock(TokenMapper::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val remoteDataSource = mock(RemoteDataSource::class.java)

    private val mainRepository =
        MainRepositoryImpl(
            tokenMapper,
            localDataSource,
            remoteDataSource
        )

    private val tokenData = TestDataFactory.createTokenData()
    private val tokenEntity = TestDataFactory.createTokenEntity()

    @Before
    fun setup() {
        stubSendAuthorization(anyString(), anyString(), Single.just(tokenData))
        stubMapFrom(tokenData, tokenEntity)
    }

    @Test
    fun sendAuthorizationCompletes() {
        val testObserver = mainRepository.sendAuthorization(anyString(), anyString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun sendAuthorizationReturnsData() {
        val testObserver = mainRepository.sendAuthorization(anyString(), anyString()).test()
        testObserver.assertValue(tokenEntity)
    }

    private fun stubMapFrom(dataModel: TokenData, domainEntity: TokenEntity) {
        Mockito.`when`(tokenMapper.mapFrom(dataModel))
            .thenReturn(domainEntity)
    }

    private fun stubSendAuthorization(
        username: String,
        password: String,
        response: Single<TokenData>
    ) {
        Mockito.`when`(remoteDataSource.sendAuthorization(username, password)).thenReturn(response)
    }

}