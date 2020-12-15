package com.example.android_party.ui.servers

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_party.R
import com.example.android_party.databinding.ServersActivityBinding
import com.example.android_party.injection.ViewModelFactory
import com.example.android_party.ui.login.LoginActivity

class ServersActivity : AppCompatActivity() {

    private lateinit var binding: ServersActivityBinding
    private lateinit var viewModel: ServersViewModel

    private var serversListAdapter: ServersListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.servers_activity)
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory()).get(ServersViewModel::class.java)
        viewModel.performLogout.observe(this, { value -> if (value) logout() })
        viewModel.loadingError.observe(this, { value ->
            showErrorToast(value)
            finish()
        })
        viewModel.loadingSuccess.observe(this, { value -> serversListAdapter?.updateList(value) })
        binding.viewModel = viewModel
        setUpServersRecyclerView(binding.serversRecyclerView)
    }

    private fun setUpServersRecyclerView(recyclerView: RecyclerView) {
        serversListAdapter = ServersListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = serversListAdapter
    }

    private fun logout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}