package com.playground.ugnius.homework

import android.content.SharedPreferences
import com.playground.ugnius.homework.interfaces.LoginView
import com.playground.ugnius.homework.model.clients.ApiClient
import com.playground.ugnius.homework.model.entities.AccessToken
import com.playground.ugnius.homework.model.entities.UserRequest
import com.playground.ugnius.homework.presenters.LoginPresenter
import io.reactivex.Scheduler
import org.mockito.ArgumentMatchers.anyString
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Test
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

     companion object {
        const val SUCCESSFUL_TEST_USERNAME = "tesonet"
        const val SUCCESSFUL_TEST_PASSWORD = "partyanimal"
        const val UNSUCCESSFUL_TEST_USERNAME = "error"
        const val UNSUCCESSFUL_TEST_PASSWORD = "error"
        const val ACCESS_TOKEN = "f9731b590611a5a9377fbd02f247fcdf"

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
    @Mock private lateinit var preferences: SharedPreferences
    @InjectMocks private lateinit var presenter: LoginPresenter

    @Test
    fun testSuccessfulLogin() {
        val userRequest = UserRequest(SUCCESSFUL_TEST_USERNAME, SUCCESSFUL_TEST_PASSWORD)
        val editor = mock(SharedPreferences.Editor::class.java)
        `when`(preferences.edit()).thenReturn(editor)
        `when`(editor.putString(anyString(), anyString())).thenReturn(editor)
        `when`(apiClient.requestToken(userRequest)).thenReturn(Single.just(AccessToken(ACCESS_TOKEN)))
        presenter.requestToken(userRequest)
        verify(view).goToServersFragment()
    }

    @Test(expected = RuntimeException::class)
    fun testUnsuccessfulLogin() {
        val userRequest = UserRequest(UNSUCCESSFUL_TEST_USERNAME, UNSUCCESSFUL_TEST_PASSWORD)
        `when`(apiClient.requestToken(userRequest)).thenThrow(RuntimeException())
        presenter.requestToken(userRequest)
        verify(view).showError()
    }
}
