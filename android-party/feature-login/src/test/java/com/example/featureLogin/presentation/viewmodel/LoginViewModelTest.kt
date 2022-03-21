package com.example.featureLogin.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.core.viewmodel.ViewState
import com.example.domainLogin.domain.model.Token
import com.example.domainLogin.domain.usecase.LoginUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {
    private val useCase: LoginUseCase = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()
    private lateinit var viewModel: LoginViewModel

    private lateinit var testDispatcher: TestDispatcher

    private fun createViewModel() {
        viewModel = LoginViewModel(
            useCase,
            savedStateHandle
        )
    }

    private fun getToken() = Token("test_token")

    @Before
    fun setUp() {
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `test success state`() = runTest {
        val resultStates: MutableList<ViewState> = mutableListOf()
        val data = getToken()
        val provided = LoginUseCase.Output.Success(data)
        val inputUserName = "test_user"
        val inputPassword = "test"
        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        every {
            savedStateHandle.set("username", inputUserName)
        } returns Unit
        every {
            savedStateHandle.get<String>("username")
        } returns inputUserName

        createViewModel()
        viewModel.onUiEvent(LoginContract.Event.OnLoginClicked(inputUserName, inputPassword))

        viewModel.viewState
            .take(1)
            .onCompletion {
                assert(resultStates.last() is LoginContract.State)
                MatcherAssert.assertThat(
                    (resultStates.last() as LoginContract.State).isLoading,
                    CoreMatchers.equalTo(false)
                )
                MatcherAssert.assertThat(
                    (resultStates.last() as LoginContract.State).userName,
                    CoreMatchers.equalTo(inputUserName)
                )
                MatcherAssert.assertThat(
                    (resultStates.last() as LoginContract.State).password,
                    CoreMatchers.equalTo(inputPassword)
                )
            }
            .collectLatest {
                resultStates.add(it)
            }
    }

    @Test
    fun `test navigation effect`() = runTest {
        val data = getToken()
        val provided = LoginUseCase.Output.Success(data)
        val inputUserName = "test_user"
        val inputPassword = "test"
        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        every {
            savedStateHandle.set("username", inputUserName)
        } returns Unit
        every {
            savedStateHandle.get<String>("username")
        } returns inputUserName

        createViewModel()
        viewModel.onUiEvent(LoginContract.Event.OnLoginClicked(inputUserName, inputPassword))

        viewModel.effect
            .take(1)
            .collect {
                assert(it is LoginContract.Effect.OnNavigationEffect)
            }
    }

    @Test
    fun `test passwordErrorEffect effect`() = runTest {
        val data = getToken()
        val provided = LoginUseCase.Output.Success(data)
        val inputUserName = "test_user"
        val inputPassword = ""
        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        every {
            savedStateHandle.set("username", inputUserName)
        } returns Unit
        every {
            savedStateHandle.get<String>("username")
        } returns inputUserName

        createViewModel()
        viewModel.onUiEvent(LoginContract.Event.OnLoginClicked(inputUserName, inputPassword))

        viewModel.effect
            .take(1)
            .collect {
                assert(it is LoginContract.Effect.PasswordErrorEffect)
            }
    }

    @Test
    fun `test userNameErrorEffect effect`() = runTest {
        val data = getToken()
        val provided = LoginUseCase.Output.Success(data)
        val inputUserName = ""
        val inputPassword = ""
        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        every {
            savedStateHandle.set("username", inputUserName)
        } returns Unit
        every {
            savedStateHandle.get<String>("username")
        } returns inputUserName

        createViewModel()
        viewModel.onUiEvent(LoginContract.Event.OnLoginClicked(inputUserName, inputPassword))

        viewModel.effect
            .take(1)
            .collect {
                assert(it is LoginContract.Effect.UserNameErrorEffect)
            }
    }

    @Test
    fun `test networkError effect`() = runTest {
        val provided = LoginUseCase.Output.NetworkError
        val inputUserName = "test_user"
        val inputPassword = "test"
        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        every {
            savedStateHandle.set("username", inputUserName)
        } returns Unit
        every {
            savedStateHandle.get<String>("username")
        } returns inputUserName

        createViewModel()
        viewModel.onUiEvent(LoginContract.Event.OnLoginClicked(inputUserName, inputPassword))

        viewModel.effect
            .take(1)
            .collect {
                assert(it is LoginContract.Effect.NetworkErrorEffect)
            }
    }

    @Test
    fun `test unknownError effect`() = runTest {
        val message = "test error"
        val provided = LoginUseCase.Output.UnknownError(message)
        val inputUserName = "test_user"
        val inputPassword = "test"
        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        every {
            savedStateHandle.set("username", inputUserName)
        } returns Unit
        every {
            savedStateHandle.get<String>("username")
        } returns inputUserName

        createViewModel()
        viewModel.onUiEvent(LoginContract.Event.OnLoginClicked(inputUserName, inputPassword))

        viewModel.effect
            .take(1)
            .collect {
                assert(it is LoginContract.Effect.UnknownErrorEffect)
                MatcherAssert.assertThat(
                    (it as LoginContract.Effect.UnknownErrorEffect).message,
                    CoreMatchers.equalTo(message)
                )
            }
    }

    @org.junit.After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
