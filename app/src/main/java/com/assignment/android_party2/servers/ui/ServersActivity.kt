package com.assignment.android_party2.servers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.android_party2.R
import com.assignment.android_party2.databinding.ActivityServersBinding
import com.assignment.android_party2.servers.models.ServerModel
import com.assignment.android_party2.servers.ui.adapter.ServersRecyclerViewAdapter
import com.assignment.android_party2.preferences.PreferencesProvider
import com.assignment.android_party2.utils.hide
import com.assignment.android_party2.utils.show
import com.assignment.android_party2.utils.toast
import kotlinx.android.synthetic.main.activity_servers.*

class ServersActivity : AppCompatActivity(), ServersCallback {

    private lateinit var sessionManager: PreferencesProvider
    private lateinit var serversViewModel: ServersViewModel

    val TAG = ServersActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityServersBinding = DataBindingUtil.setContentView(this, R.layout.activity_servers)
        serversViewModel = ViewModelProviders.of(this).get(ServersViewModel::class.java)
        binding.serversViewModel = serversViewModel
        serversViewModel.serversCallback = this

        sessionManager = PreferencesProvider(this)

        serversViewModel.getServersFromDb().observe(this, Observer<List<ServerModel>> { serverList ->
            loadingLayout.hide()
            setupServersRecyclerView(serverList!!)
            Log.d(TAG, "Cached servers in room db:$serverList")
        })

        serversViewModel.getServersFromApiAndStore(sessionManager.fetchLoginToken())

        logout.setOnClickListener {
            finish()
        }
    }

    override fun onStarted() {
        loadingLayout.show()
    }

    override fun onFailure(errorMessage: String) {
        loadingLayout.hide()
        toast(errorMessage)
    }

    fun setupServersRecyclerView(servers: List<ServerModel>) {
        val recyclerViewAdapter = ServersRecyclerViewAdapter(this, servers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
    }
}
