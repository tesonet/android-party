package lt.havefun.tesonetfunparty.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import lt.havefun.tesonetfunparty.MockedApi
import lt.havefun.tesonetfunparty.MockedPreferencesManager
import lt.havefun.tesonetfunparty.RxSchedulerRule
import lt.havefun.tesonetfunparty.data.LoginRepository
import lt.havefun.tesonetfunparty.ui.login.LoginViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class LoginViewModelUnitTest {

    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    lateinit var observer: Observer<String>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val mockedApi = MockedApi()
        val mockedPreferencesManager =
            MockedPreferencesManager()
        loginViewModel = LoginViewModel(LoginRepository(mockedApi), mockedPreferencesManager)
    }

    @Test
    fun valuesValid() {
        loginViewModel.validationError.observeForever(observer)
        loginViewModel.login("test","test")

        verify(observer, never()).onChanged("")
    }

    @Test
    fun tokenReceived() {
        loginViewModel.data.observeForever(observer)
        loginViewModel.login("test","test")

        verify(observer).onChanged("testToken")
    }

    @Test
    fun emptyTokenReceived() {
        loginViewModel.data.observeForever(observer)
        loginViewModel.login("test","test")

        verify(observer, never()).onChanged("")
    }

    @Test
    fun wrongCredentials() {
        loginViewModel.data.observeForever(observer)
        loginViewModel.login("nottest","nottest")

        verify(observer, never()).onChanged("testToken")
    }

    @Test
    fun valuesNotEmpty() {
        loginViewModel.validationError.observeForever(observer)
        loginViewModel.login("","")

        verify(observer).onChanged("")
    }

    @Test
    fun valueNotEmpty() {
        loginViewModel.validationError.observeForever(observer)
        loginViewModel.login("","1")

        verify(observer).onChanged("")
    }
}