package levkovskiy.dev.tesonettest.ui.login

import android.content.Intent
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_login.et_username
import kotlinx.android.synthetic.main.activity_login.login
import levkovskiy.dev.tesonettest.R
import levkovskiy.dev.tesonettest.mvp.presenter.LoginPresenter
import levkovskiy.dev.tesonettest.mvp.view.LoginView
import levkovskiy.dev.tesonettest.ui.base.BaseActivity
import levkovskiy.dev.tesonettest.ui.loading.LoadingActivity
import levkovskiy.dev.tesonettest.utils.PreferenceHelper

class LoginActivity : BaseActivity(), LoginView {

  override fun error() {
    showMessageSnackbar("Can't authorize")
  }

  override fun success(token: String) {
    PreferenceHelper.setStringPreference(this, PreferenceHelper.token, token)
    startMainActivity()
  }
  private fun startMainActivity(){
    val intent = Intent(this, LoadingActivity::class.java)
    startActivity(intent)
    finish()
  }

  override fun getLayoutRes() = R.layout.activity_login

  var loginPresenter = LoginPresenter(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (PreferenceHelper.isNeedToLogin(this)){
      startMainActivity()
    }
    login.setOnClickListener {
      val username = et_username.text.toString()
      val password = et_password.text.toString()
      if (!username.isEmpty() && !password.isEmpty())
        loginPresenter.login(username, password)
      else
        showMessageSnackbar("Fill all data")

    }
  }


}
