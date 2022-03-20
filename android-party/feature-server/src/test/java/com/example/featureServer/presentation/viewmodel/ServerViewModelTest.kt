package com.example.featureServer.presentation.viewmodel

import com.example.core.viewmodel.ViewState
import com.example.domainServer.domain.usecase.FetchServerUseCase
import com.example.featureServer.presentation.mapper.DomainToUiMapper
import com.example.featureServer.presentation.utils.TestData
import com.example.featureServer.presentation.viewmodel.ServerContract.State
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ServerViewModelTest {
    private val useCase: FetchServerUseCase = mockk()
    private lateinit var viewModel: ServerViewModel

    private lateinit var testDispatcher: TestDispatcher

    private fun createViewModel() {
        viewModel = ServerViewModel(
            fetchServer = useCase
        )
    }

    @Before
    fun setUp() {
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `test success state`() = runTest {
        val resultStates: MutableList<ViewState> = mutableListOf()
        val data = TestData.getServerList()
        val provided = FetchServerUseCase.Output.Success(data)
        val mapper = DomainToUiMapper()
        val expected = provided.servers.map { mapper.map(it) }
        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        createViewModel()
        viewModel.onUiEvent(ServerContract.Event.OnLoadingOpened)

        viewModel.viewState
            .take(1)
            .onCompletion {
                assert(resultStates.last() is State)
                MatcherAssert.assertThat(
                    (resultStates.last() as State).serverList,
                    CoreMatchers.equalTo(expected)
                )
            }
            .collectLatest {
                resultStates.add(it)
            }
    }

    @Test
    fun `test navigation effect`() = runTest {
        val data = TestData.getServerList()
        val provided = FetchServerUseCase.Output.Success(data)

        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        createViewModel()
        viewModel.onUiEvent(ServerContract.Event.OnLoadingOpened)

        viewModel.effect
            .take(1)
            .collect {
                assert(it is ServerContract.Effect.OnNavigationEffect)
            }
    }

    @Test
    fun `test network error effect`() = runTest {
        val provided = FetchServerUseCase.Output.NetworkError
        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        createViewModel()
        viewModel.onUiEvent(ServerContract.Event.OnLoadingOpened)

        viewModel.effect.take(1)
            .collect {
                assert(it is ServerContract.Effect.NetworkErrorEffect)
            }
    }

    @Test
    fun `test unknownError state`() = runTest {
        val message = "test  error"
        val provided = FetchServerUseCase.Output.UnknownError(message)
        coEvery {
            useCase.execute(any())
        } returns flow { emit(provided) }

        createViewModel()
        viewModel.onUiEvent(ServerContract.Event.OnLoadingOpened)

        viewModel.effect
            .take(1)
            .collectLatest {
                assert(it is ServerContract.Effect.UnknownErrorEffect)
                MatcherAssert.assertThat(
                    (it as ServerContract.Effect.UnknownErrorEffect).message,
                    CoreMatchers.equalTo(message)
                )
            }
    }


    @Test
    fun `test logout click`() = runTest {
        createViewModel()
        viewModel.onUiEvent(ServerContract.Event.OnLogoutClicked)
        viewModel.effect
            .take(1)
            .collectLatest {
                assert(it is ServerContract.Effect.OnLogoutEffect)
            }
    }

    @org.junit.After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}