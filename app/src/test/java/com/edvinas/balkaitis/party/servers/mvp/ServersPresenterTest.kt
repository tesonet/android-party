package com.edvinas.balkaitis.party.servers.mvp

import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.network.Server
import com.edvinas.balkaitis.party.servers.network.ServersService
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ServersPresenterTest {
    @Mock private lateinit var tokenStorage: TokenStorage
    @Mock private lateinit var view: ServersContract.View
    @Mock private lateinit var serversService: ServersService

    private lateinit var presenter: ServersPresenter

    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        presenter = ServersPresenter(testScheduler, tokenStorage, serversService)
        presenter.takeView(view)
    }

    @Test
    fun onLogoutClicked_removesTokenAndShowsLogin() {
        presenter.onLogoutClicked()

        verify(tokenStorage).removeToken()
        verify(view).showLogin()
    }

    @Test
    fun onCreated_setsList() {
        presenter.onCreated(emptyArray())

        verify(view).setList()
    }

    @Test
    fun onCreated_whenArrayIsNotNull_populatesServers() {
        val servers = arrayOf(Server(COUNTRY, DISTANCE))

        presenter.onCreated(servers)

        verify(view).populateServers(servers)
    }

    @Test
    fun onCreated_whenArrayIsNullAndServerDownloadSucceeds_populatesServers() {
        val serversList = listOf(Server(COUNTRY, DISTANCE))
        given(serversService.getServers()).willReturn(Single.just(serversList))

        presenter.onCreated(null)
        testScheduler.triggerActions()

        verify(view).populateServers(serversList.toTypedArray())
    }

    @Test
    fun onCreated_whenArrayIsNullAndServerDownloadFails_showsError() {
        val throwable = Throwable(ERROR_MESSAGE)
        given(serversService.getServers()).willReturn(Single.error(throwable))

        presenter.onCreated(null)
        testScheduler.triggerActions()

        verify(view).showError(ERROR_MESSAGE)
    }

    companion object {
        private const val COUNTRY = "Lithuania #1"
        private const val DISTANCE = "0 km"
        private const val ERROR_MESSAGE = "errorMessage"
    }
}
