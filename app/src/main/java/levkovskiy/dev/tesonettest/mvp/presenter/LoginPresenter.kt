package levkovskiy.dev.tesonettest.mvp.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import levkovskiy.dev.tesonettest.mvp.view.LoginView
import levkovskiy.dev.tesonettest.net.Api

open class LoginPresenter(view: LoginView) : BasePresenter() {
  private val loginView = view
  private val netApi = Api.create()

  fun login(
    username: String,
    password: String
  ) {
    netApi.login(username, password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          loginView.success(it.token)
        }, {
          loginView.error()
          it.printStackTrace()
        })

  }

}