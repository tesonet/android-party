package com.edvinas.balkaitis.party.servers.mvp

import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.network.Server
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ServersPresenterTest {
    @Mock private lateinit var tokenStorage: TokenStorage
    @Mock private lateinit var view: ServersContract.View

    private lateinit var presenter: ServersPresenter

    @Before
    fun setUp() {
        presenter = ServersPresenter(tokenStorage)
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

    companion object {
        private const val COUNTRY = "Lithuania #1"
        private const val DISTANCE = "0 km"
    }
}
