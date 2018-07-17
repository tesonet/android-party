package levkovskiy.dev.tesonettest.ui.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import levkovskiy.dev.tesonettest.mvp.view.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(getLayoutRes())
  }

  fun showMessageSnackbar(message: String) {
    val viewGroup =
      (this.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
    Snackbar.make(viewGroup, message, Snackbar.LENGTH_LONG)
        .show()
  }

  abstract fun getLayoutRes(): Int
}