package com.edvinas.balkaitis.party.login.mvp

import com.edvinas.balkaitis.party.login.network.LoginBody
import com.edvinas.balkaitis.party.login.network.LoginResponse
import com.edvinas.balkaitis.party.login.network.LoginService
import com.edvinas.balkaitis.party.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.network.Server
import com.edvinas.balkaitis.party.servers.network.ServersService
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {
    @Mock private lateinit var view: LoginContract.View
    @Mock private lateinit var loginService: LoginService
    @Mock private lateinit var tokenStorage: TokenStorage
    @Mock private lateinit var serversService: ServersService

    private lateinit var presenter: LoginPresenter

    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        presenter = LoginPresenter(testScheduler, loginService, tokenStorage, serversService)
        presenter.takeView(view)
    }

    @Test
    fun onLoginClicked_onLoginSuccess_savesTokenAndShowsLoadingView() {
        val loginResponse = LoginResponse(TOKEN)
        val loginBody = LoginBody(USERNAME, PASSWORD)
        given(loginService.login(loginBody)).willReturn(Single.just(loginResponse))

        presenter.onLoginClicked(USERNAME, PASSWORD)
        testScheduler.triggerActions()

        verify(tokenStorage).saveToken(TOKEN)
        verify(view).showLoadingView()
    }

    @Test
    fun onLoginClicked_onLoginError_showsErrorMessageAndHidesLoadingView() {
        val loginBody = LoginBody(USERNAME, PASSWORD)
        given(loginService.login(loginBody)).willReturn(Single.error(Throwable(ERROR_MESSAGE)))

        presenter.onLoginClicked(USERNAME, PASSWORD)
        testScheduler.triggerActions()

        verify(view).hideLoadingView()
        verify(view).showError(ERROR_MESSAGE)
    }

    @Test
    fun onLoginClicked_onGetServersSuccess_showsServers() {
        val loginResponse = LoginResponse(TOKEN)
        val servers = listOf(Server("Spain", "9001"))
        val loginBody = LoginBody(USERNAME, PASSWORD)
        given(loginService.login(loginBody)).willReturn(Single.just(loginResponse))
        given(serversService.getServers("Bearer $TOKEN")).willReturn(Single.just(servers))

        presenter.onLoginClicked(USERNAME, PASSWORD)
        testScheduler.triggerActions()

        verify(view).showServers(servers)
    }

    @Test
    fun onLoginClicked_onGetServersError_showsErrorMessageAndHidesLoadingView() {
        val loginBody = LoginBody(USERNAME, PASSWORD)
        given(loginService.login(loginBody)).willReturn(Single.just(LoginResponse(TOKEN)))
        given(serversService.getServers("Bearer $TOKEN")).willReturn(Single.error(Throwable(ERROR_MESSAGE)))

        presenter.onLoginClicked(USERNAME, PASSWORD)
        testScheduler.triggerActions()

        verify(view).hideLoadingView()
        verify(view).showError(ERROR_MESSAGE)
    }

    companion object {
        private const val TOKEN = "token"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val ERROR_MESSAGE = "errorMessage"
    }
}
