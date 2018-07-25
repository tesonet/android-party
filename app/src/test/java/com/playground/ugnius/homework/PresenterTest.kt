package com.playground.ugnius.homework

import com.playground.ugnius.homework.interfaces.LoginView
import com.playground.ugnius.homework.model.ServersRepository
import com.playground.ugnius.homework.model.clients.ApiClient
import com.playground.ugnius.homework.model.entities.AccessToken
import com.playground.ugnius.homework.model.entities.UserRequest
import com.playground.ugnius.homework.presenters.LoginPresenter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.realm.Realm
import org.junit.Before
import org.mockito.ArgumentMatchers.anyList
import org.junit.Test
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PresenterTest {

    companion object {
        private const val SUCCESSFUL_TEST_USERNAME = "tesonet"
        private const val SUCCESSFUL_TEST_PASSWORD = "partyanimal"
        private const val UNSUCCESSFUL_TEST_USERNAME = "error"
        private const val UNSUCCESSFUL_TEST_PASSWORD = "error"
        private const val ACCESS_TOKEN = "f9731b590611a5a9377fbd02f247fcdf"

        @BeforeClass
        @JvmStatic
        fun overrideSchedulers() {
            val immediate = object : Scheduler() {
                override fun createWorker() = ExecutorScheduler.ExecutorWorker(Runnable::run)
            }
            RxJavaPlugins.setInitIoSchedulerHandler { immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
        }
    }

    @Mock private lateinit var view: LoginView
    @Mock private lateinit var apiClient: ApiClient
    @Mock private lateinit var realm: Realm
    @InjectMocks private lateinit var serversRepository: ServersRepository
    private lateinit var presenter: LoginPresenter

    @Before
    fun setup() {
        presenter = LoginPresenter(view, apiClient, serversRepository)
    }

    @Test
    fun testSuccessfulLogin() {
        val userRequest = UserRequest(SUCCESSFUL_TEST_USERNAME, SUCCESSFUL_TEST_PASSWORD)
        val accessToken = AccessToken(ACCESS_TOKEN)
        `when`(apiClient.requestToken(userRequest)).thenReturn(Single.just(accessToken))
        `when`(apiClient.getServers("Bearer ${accessToken.token}")).thenReturn(Single.just(anyList()))
        presenter.login(userRequest)
        verify(view, timeout(2000)).goToServersFragment()
    }

    @Test(expected = RuntimeException::class)
    fun testUnsuccessfulLogin() {
        val userRequest = UserRequest(UNSUCCESSFUL_TEST_USERNAME, UNSUCCESSFUL_TEST_PASSWORD)
        `when`(apiClient.requestToken(userRequest)).thenThrow(RuntimeException())
        presenter.login(userRequest)
        verify(view).showError()
    }
}
