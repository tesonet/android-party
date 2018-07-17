package levkovskiy.dev.tesonettest.mvp.presenter

import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import levkovskiy.dev.tesonettest.mvp.view.LoginView
import levkovskiy.dev.tesonettest.net.Api
import levkovskiy.dev.tesonettest.net.Model.Login
import levkovskiy.dev.tesonettest.net.Model.Server
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.Mockito.anyString

@RunWith(AndroidJUnit4::class)
class LoginPresenterTest {
  var loginView: LoginView = mock()
  var loginPresenter: LoginPresenter = mock()

  var api: Api = mock()
  private var username = "tesonet"
  private var pass = "partyanimal"

  @Before
  fun setup() {

    `when`(api.login(username, pass)).thenReturn(
        Observable.just(Login("test"))
    )
  }

  @Test
  fun loginSuccess() {

    verify(api).login(username, pass)
    verify(loginView).success(ArgumentMatchers.anyString())
  }

}