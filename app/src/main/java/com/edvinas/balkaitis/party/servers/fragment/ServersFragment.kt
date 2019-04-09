package com.edvinas.balkaitis.party.servers.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.edvinas.balkaitis.party.R
import com.edvinas.balkaitis.party.base.BaseDaggerFragment
import com.edvinas.balkaitis.party.login.fragment.LoginFragment
import com.edvinas.balkaitis.party.servers.list.ServersAdapter
import com.edvinas.balkaitis.party.servers.mvp.ServersContract
import com.edvinas.balkaitis.party.servers.network.Server
import com.edvinas.balkaitis.party.utils.extensions.replaceFragment
import kotlinx.android.synthetic.main.fragment_servers.*
import javax.inject.Inject

class ServersFragment : BaseDaggerFragment(), ServersContract.View {

    @Inject lateinit var presenter: ServersContract.Presenter
    @Inject lateinit var adapter: ServersAdapter

    override fun getLayoutId() = R.layout.fragment_servers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreated(arguments?.getParcelableArray(KEY_SERVERS) as Array<Server>?)
        iconLogout.setOnClickListener { presenter.onLogoutClicked() }
    }

    override fun showLogin() {
        val generalErrorMessage = getString(R.string.general_error_something_wrong)
        activity?.replaceFragment(LoginFragment.newInstance())
                ?: Toast.makeText(requireContext(), generalErrorMessage, Toast.LENGTH_LONG).show()
    }

    override fun setList() {
        serversList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        serversList.setHasFixedSize(true)
        serversList.adapter = adapter
    }

    override fun populateServers(servers: Array<Server>) {
        adapter.setAll(servers.toList())
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    companion object {
        private const val KEY_SERVERS = "key.servers"
        fun newInstance(servers: List<Server>? = null): ServersFragment {
            return ServersFragment().apply {
                arguments = Bundle().apply { putParcelableArray(KEY_SERVERS, servers?.toTypedArray()) }
            }
        }
    }
}
