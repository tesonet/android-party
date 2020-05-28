package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.TestDataFactory
import com.baruckis.androidparty.data.mapper.LoggedInUserMapper
import com.baruckis.androidparty.data.mapper.ServerMapper
import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.mock

class MainRepositoryImplTest {

    private val loggedInUserMapper = mock(LoggedInUserMapper::class.java)
    private val serverMapper = mock(ServerMapper::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val remoteDataSource = mock(RemoteDataSource::class.java)

    private val mainRepository =
        MainRepositoryImpl(
            loggedInUserMapper,
            serverMapper,
            localDataSource,
            remoteDataSource
        )

    private val tokenData = TestDataFactory.createTokenData()

    private val loggedInUserData = TestDataFactory.createLoggedInUserData()
    private val loggedInUserEntity = TestDataFactory.createLoggedInUserEntity()

    @Before
    fun setup() {
        stubSendAuthorization(
            TestDataFactory.username,
            TestDataFactory.password,
            Single.just(tokenData)
        )
        stubSetLoggedInUser()
        stubLoggedInUserMapperMapFrom(loggedInUserData, loggedInUserEntity)
    }

    @Test
    fun loginCompletes() {
        val testObserver =
            mainRepository.login(TestDataFactory.username, TestDataFactory.password).test()
        testObserver.assertComplete()
    }

    @Test
    fun loginReturnsData() {
        val testObserver =
            mainRepository.login(TestDataFactory.username, TestDataFactory.password).test()
        testObserver.assertValue(loggedInUserEntity)
    }

    private fun stubLoggedInUserMapperMapFrom(
        dataModel: LoggedInUserData,
        domainEntity: LoggedInUserEntity
    ) {
        Mockito.`when`(loggedInUserMapper.mapFromData(dataModel))
            .thenReturn(domainEntity)
    }

    private fun stubSendAuthorization(
        username: String,
        password: String,
        response: Single<TokenData>
    ) {
        Mockito.`when`(remoteDataSource.sendAuthorization(username, password)).thenReturn(response)
    }

    private fun stubSetLoggedInUser() {
        doNothing().`when`(localDataSource).setLoggedInUser(any())
    }

}