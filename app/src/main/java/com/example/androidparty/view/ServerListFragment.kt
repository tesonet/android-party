package com.example.androidparty.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.androidparty.R
import com.example.androidparty.adapters.ServersAdapter
import com.example.androidparty.databinding.FragmentServerListBinding
import com.example.androidparty.viewmodel.ServersListViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ServerListFragment(
    val listener: FragmentsNavigationListener
) : DaggerFragment() {

    @Inject
    lateinit var serversListViewModel: ServersListViewModel

    lateinit var recyclerView: RecyclerView
    lateinit var binder: FragmentServerListBinding
    lateinit var serverListAdapter: ServersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_server_list, container, false)
        recyclerView = binder.serverListRecycler
        recyclerView.layoutManager = LinearLayoutManager(activity)
        serverListAdapter = ServersAdapter(listOf())
        recyclerView.adapter = serverListAdapter
        serversListViewModel.getServersList()
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binder.logoutButton.setOnClickListener { listener.onLogoutClicked() }

        serversListViewModel.mServersList.observe(this, Observer {
            serverListAdapter.setData(it)
            serverListAdapter.notifyDataSetChanged()
        })

        return binder.root
    }


    companion object {

        @JvmStatic
        fun newInstance(listener: FragmentsNavigationListener) =
            ServerListFragment(listener).apply {

            }
    }
}
