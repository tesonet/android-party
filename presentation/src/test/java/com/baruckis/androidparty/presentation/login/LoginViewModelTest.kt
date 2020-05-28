package com.baruckis.androidparty.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.usecases.FetchServersUseCase
import com.baruckis.androidparty.domain.usecases.LoginUseCase
import com.baruckis.androidparty.presentation.TestDataFactory
import com.baruckis.androidparty.presentation.mapper.LoginPresentationMapper
import com.baruckis.androidparty.presentation.mapper.ServerPresentationMapper
import com.baruckis.androidparty.presentation.util.any
import com.baruckis.androidparty.presentation.util.argumentCaptor
import com.baruckis.androidparty.presentation.util.capture
import com.baruckis.androidparty.presentation.util.eq
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Rule
import org.junit.Test
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.*
import kotlin.test.assertEquals


class LoginViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var loginUseCase: LoginUseCase = mock(LoginUseCase::class.java)
    var fetchServersUseCase: FetchServersUseCase = mock(FetchServersUseCase::class.java)
    var loginPresentationMapper: LoginPresentationMapper = mock(LoginPresentationMapper::class.java)
    var serverPresentationMapper: ServerPresentationMapper =
        mock(ServerPresentationMapper::class.java)

    var viewModel = LoginViewModel(
        loginUseCase,
        fetchServersUseCase,
        loginPresentationMapper,
        serverPresentationMapper
    )

    @Captor
    val captor = argumentCaptor<DisposableSingleObserver<LoggedInUserEntity>>()


    @Test
    fun loginExecute() {

        viewModel.login(TestDataFactory.username, TestDataFactory.password, 0)

        verify(loginUseCase, times(1)).execute(
            any(),
            eq(
                LoginUseCase.Params.authorization(
                    TestDataFactory.username,
                    TestDataFactory.password
                )
            )
        )

    }

    @Test
    fun loginReturnsData() {

        val domainEntity = TestDataFactory.createLoggedInUserEntity()
        val presentationModel = TestDataFactory.createLoginPresentation()

        Mockito.`when`(loginPresentationMapper.mapToPresentation(domainEntity))
            .thenReturn(presentationModel)

        viewModel.login(TestDataFactory.username, TestDataFactory.password, 0)

        verify(loginUseCase, times(1)).execute(
            capture(captor),
            eq(
                LoginUseCase.Params.authorization(
                    TestDataFactory.username,
                    TestDataFactory.password
                )
            )
        )

        captor.value.onSuccess(domainEntity)

        assertEquals(presentationModel, viewModel.loginResource.value?.data)

    }

    @Test
    fun loginReturnsErrorMessage() {

        val errorMsg = TestDataFactory.createErrorMessage()

        viewModel.login(TestDataFactory.username, TestDataFactory.password, 0)

        verify(loginUseCase, times(1)).execute(
            capture(captor),
            eq(
                LoginUseCase.Params.authorization(
                    TestDataFactory.username,
                    TestDataFactory.password
                )
            )
        )

        captor.value.onError(RuntimeException(errorMsg))

        assertEquals(errorMsg, viewModel.loginResource.value?.message)
    }

}