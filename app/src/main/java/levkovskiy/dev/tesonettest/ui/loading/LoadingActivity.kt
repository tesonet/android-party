package levkovskiy.dev.tesonettest.ui.loading

import android.content.Intent
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import levkovskiy.dev.tesonettest.R
import levkovskiy.dev.tesonettest.ui.base.BaseActivity
import levkovskiy.dev.tesonettest.ui.main.MainActivity
import java.util.concurrent.TimeUnit

class LoadingActivity : BaseActivity() {
  override fun getLayoutRes() = R.layout.activity_loading

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Observable.timer(5000, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          startNextScreen()
          finish()
        }
  }

  private fun startNextScreen() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
  }
}
