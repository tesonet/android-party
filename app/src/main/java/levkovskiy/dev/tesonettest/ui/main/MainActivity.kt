package levkovskiy.dev.tesonettest.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.serverList
import kotlinx.android.synthetic.main.activity_main.toolbar
import levkovskiy.dev.tesonettest.R
import levkovskiy.dev.tesonettest.mvp.presenter.MainPresenter
import levkovskiy.dev.tesonettest.mvp.view.MainView
import levkovskiy.dev.tesonettest.net.Model.Server
import levkovskiy.dev.tesonettest.ui.base.BaseActivity
import levkovskiy.dev.tesonettest.utils.PreferenceHelper

class MainActivity : BaseActivity(), MainView {
  override fun logout() {
    finish()
  }

  private lateinit var mainPresenter: MainPresenter
  private var adapter = MainAdapter(ArrayList()) {
    showMessageSnackbar(it.name)
  }

  override fun successLoad(list: List<Server>) {
    adapter.list = list as ArrayList<Server>
    adapter.notifyDataSetChanged()
  }

  override fun errorLoad() {
    showMessageSnackbar("Can't load data")
  }

  override fun getLayoutRes() = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(toolbar)
    val token = PreferenceHelper.getStringPreference(this, PreferenceHelper.token)
    mainPresenter = MainPresenter(this, token!!)
    serverList.adapter = adapter
    serverList.setHasFixedSize(true)

    mainPresenter.getServers()
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    mainPresenter.logout(this)
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.main_menu, menu)
    return true
  }
}
