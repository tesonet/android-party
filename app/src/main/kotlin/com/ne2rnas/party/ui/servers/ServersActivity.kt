package com.ne2rnas.party.ui.servers

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ne2rnas.party.R
import com.ne2rnas.party.base.BaseActivity
import com.ne2rnas.party.data.servers.Server
import com.ne2rnas.party.databinding.ActivityServersBinding
import com.ne2rnas.party.ui.login.LoginActivity
import kotlinx.android.synthetic.main.servers_loader.*
import kotlinx.android.synthetic.main.toolbar.*

class ServersActivity : BaseActivity<ServersPresenter>(), ServersView {

    private lateinit var binding: ActivityServersBinding
    private val serversAdapter = ServersAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_servers)
        binding.adapter = serversAdapter
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        setActionBar()
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.show()
        binding.serversLoaderVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.hide()
        binding.serversLoaderVisibility = View.GONE
    }

    override fun showAdapter() {
        binding.adapterVisibility = View.VISIBLE
    }

    override fun hideAdapter() {
        binding.adapterVisibility = View.GONE
    }

    override fun instantiatePresenter(): ServersPresenter {
        return ServersPresenter(this)
    }

    override fun updateServers(servers: List<Server>) {
        serversAdapter.updateServers(servers)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                presenter.resetUserCredentials()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}
