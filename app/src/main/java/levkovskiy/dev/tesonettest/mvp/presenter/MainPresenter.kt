package levkovskiy.dev.tesonettest.mvp.presenter

import android.content.Context
import android.content.Intent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import levkovskiy.dev.tesonettest.mvp.view.MainView
import levkovskiy.dev.tesonettest.net.Api
import levkovskiy.dev.tesonettest.ui.login.LoginActivity
import levkovskiy.dev.tesonettest.utils.PreferenceHelper

open class MainPresenter(
  private val mainView: MainView,
  token: String
) : BasePresenter() {

  private val api = Api.create(token)

  fun logout(context: Context) {
    PreferenceHelper.setStringPreference(context, PreferenceHelper.token, null)
    val intent = Intent(context, LoginActivity::class.java)
    context.startActivity(intent)
    mainView.logout()
  }

  open fun getServers() {
    api.servers()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          mainView.successLoad(it)
        }, {
          mainView.errorLoad()
          it.printStackTrace()
        })
  }

}