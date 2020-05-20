package com.baruckis.domain.usecases

import com.baruckis.domain.entity.TokenEntity
import com.baruckis.domain.executor.ExecutionThreadScheduler
import com.baruckis.domain.repository.MainRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SendAuthorizationTest {

    private lateinit var sendAuthorization: SendAuthorization

    @Mock
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var backgroundExecutor: ExecutionThreadScheduler

    @Mock
    lateinit var foregroundExecutor: ExecutionThreadScheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sendAuthorization =
            SendAuthorization(mainRepository, backgroundExecutor, foregroundExecutor)
    }

    @Test
    fun sendAuthorizationCompletes() {
        stubSendAuthorization(Single.just(makeToken()))
        val testObserver = sendAuthorization.buildUseCaseSingle(
            SendAuthorization.Params.authorization(anyString(), anyString())
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun sendAuthorizationReturnsData() {
        val token = makeToken()
        stubSendAuthorization(Single.just(token))
        val testObserver = sendAuthorization.buildUseCaseSingle(
            SendAuthorization.Params.authorization(anyString(), anyString())
        ).test()
        testObserver.assertValue(token)
    }

    private fun stubSendAuthorization(single: Single<TokenEntity>) {
        Mockito.`when`(mainRepository.sendAuthorization(anyString(), anyString()))
            .thenReturn(single)
    }

    private fun makeToken(): TokenEntity {
        return TokenEntity("f9731b590611a5a9377fbd02f247fcdf")
    }

}