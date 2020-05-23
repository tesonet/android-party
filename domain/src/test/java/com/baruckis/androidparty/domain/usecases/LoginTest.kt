package com.baruckis.androidparty.domain.usecases

import com.baruckis.androidparty.domain.entity.TokenEntity
import com.baruckis.androidparty.domain.qualifiers.Background
import com.baruckis.androidparty.domain.qualifiers.Foreground
import com.baruckis.androidparty.domain.repository.MainRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginTest {

    private lateinit var login: Login

    @Mock
    lateinit var mainRepository: MainRepository

    @Mock
    @Background
    lateinit var backgroundScheduler: Scheduler

    @Mock
    @Foreground
    lateinit var foregroundScheduler: Scheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        login =
            Login(
                mainRepository,
                backgroundScheduler,
                foregroundScheduler
            )
    }

    @Test
    fun sendAuthorizationCompletes() {
        stubSendAuthorization(Single.just(makeToken()))
        val testObserver = login.buildUseCaseSingle(
            Login.Params.authorization(anyString(), anyString())
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun sendAuthorizationReturnsData() {
        val token = makeToken()
        stubSendAuthorization(Single.just(token))
        val testObserver = login.buildUseCaseSingle(
            Login.Params.authorization(anyString(), anyString())
        ).test()
        testObserver.assertValue(token)
    }

    private fun stubSendAuthorization(single: Single<TokenEntity>) {
        Mockito.`when`(mainRepository.login(anyString(), anyString()))
            .thenReturn(single)
    }

    private fun makeToken(): TokenEntity {
        return TokenEntity("f9731b590611a5a9377fbd02f247fcdf")
    }

}