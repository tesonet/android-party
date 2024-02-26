package test.servers

import core.data.repository.TokenRepository
import core.ui.state.Loading
import feature.main.data.model.Server
import feature.main.data.repository.ServersRepository
import feature.main.domain.GetServersUseCase
import feature.main.domain.IsSignedInUseCase
import feature.main.domain.SignOutUseCase
import feature.main.domain.SyncServersUseCase
import feature.main.ui.state.ServersVm
import feature.main.ui.state.sampleServers
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import test.core.MainDispatcherRule
import test.core.assertServers

class ServersVmTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val tokenRepository = mock<TokenRepository>()
    private val serversRepository = mock<ServersRepository>()

    @Test
    fun signedOut() = runTest {
        setServers(flowOf(persistentListOf()))
        setToken(null)
        val (servers, loading, error, signedIn) = viewModel().uiState.first()
        assertServers(servers)
        assertEquals(Loading.PlaceholderBasic, loading)
        assertEquals(ServersVm.Error.NoAccessToken, error)
        assertFalse(signedIn)
    }

    @Test
    fun serversSaved() = runTest {
        setToken()
        val sampleServers = sampleServers()
        setServers(flowOf(sampleServers))
        val actual = viewModel().uiState.first()
        val expected = ServersVm.UiState(sampleServers, null, null, true)
        assertEquals(expected, actual)
    }

    @Test
    fun serversSync() = runTest {
        setToken()
        val flow = MutableStateFlow<ImmutableList<Server>>(persistentListOf())
        setServers(flow)
        val serversVm = viewModel()
        val (servers, loading, error, signedIn) = serversVm.uiState.first()
        assertServers(servers)
        assertEquals(Loading.PlaceholderBasic, loading)
        assertEquals(null, error)
        assertTrue(signedIn)
        val sampleServers = sampleServers()
        flow.emit(sampleServers)
        val actual = serversVm.uiState.first()
        val expected = ServersVm.UiState(sampleServers, null, null, true)
        assertEquals(expected, actual)
    }

    private fun viewModel(): ServersVm {
        val getServersUseCase = GetServersUseCase(serversRepository)
        val syncServersUseCase = SyncServersUseCase(tokenRepository, serversRepository)
        val signOutUseCase = SignOutUseCase(tokenRepository, serversRepository)
        val isSignedInUseCase = IsSignedInUseCase(tokenRepository)
        return ServersVm(getServersUseCase, syncServersUseCase, signOutUseCase, isSignedInUseCase)
    }

    private suspend fun setToken(token: String? = "f9731b590611a5a9377fbd02f247fcdf") {
        whenever(tokenRepository.localTokens()).thenReturn(flowOf(token))
        whenever(tokenRepository.savedToken()).thenReturn(token)
    }

    private fun setServers(flowOf: Flow<ImmutableList<Server>>) {
        whenever(serversRepository.servers()).thenReturn(flowOf)
    }
}