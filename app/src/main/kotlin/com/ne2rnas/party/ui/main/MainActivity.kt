package com.ne2rnas.party.ui.main

import android.content.Intent
import android.os.Bundle
import com.ne2rnas.party.base.BaseActivity
import com.ne2rnas.party.ui.login.LoginActivity
import com.ne2rnas.party.ui.servers.ServersActivity

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }


    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun openServersActivity() {
        val intent = Intent(this, ServersActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
