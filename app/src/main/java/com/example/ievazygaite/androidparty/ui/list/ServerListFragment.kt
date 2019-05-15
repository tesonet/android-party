package com.example.ievazygaite.androidparty.ui.list

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ievazygaite.androidparty.R
import com.example.ievazygaite.androidparty.data.server.Server
import com.example.ievazygaite.androidparty.di.component.DaggerFragmentComponent
import com.example.ievazygaite.androidparty.di.module.FragmentModule
import com.example.ievazygaite.androidparty.ui.main.MainActivity
import com.example.ievazygaite.androidparty.utils.Tools.Companion.showSnackBar
import kotlinx.android.synthetic.main.fragment_server.*
import javax.inject.Inject

class ServerListFragment : Fragment(), ServerListContract.View {

    companion object {
        val TAG: String = "ServerListFragment"
    }

    @Inject
    lateinit var presenter: ServerListContract.Presenter

    private lateinit var rootView: View
    private lateinit var serverListAdapter: ServerListAdapter

    fun newInstance(servers: List<Server>?): ServerListFragment {
        return ServerListFragment().apply {
            arguments = Bundle().apply { putParcelableArray("servers", servers!!.toTypedArray()) }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_server, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showErrorMessage(error: String) {
        showSnackBar(error, Color.RED, view!!)
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
        listComponent.inject(this)
    }

    private fun initView() {
        initRecyclerView()
        presenter.getServerList(arguments!!.getParcelableArray("servers") as Array<Server>)
        btn_logout.setOnClickListener {
            presenter.logout()
        }

    }

    private fun initRecyclerView() {
        with(recycler_view) {
            layoutManager = android.support.v7.widget.LinearLayoutManager(
                requireContext(),
                android.widget.LinearLayout.VERTICAL,
                false
            )
            this@ServerListFragment.serverListAdapter = ServerListAdapter(arrayListOf(), requireContext())
            adapter = this@ServerListFragment.serverListAdapter
        }
    }

    override fun showLoginFragment() {
        (activity as MainActivity).showLoginFragment()
    }

    override fun showList(list: List<Server>) {
        if (list.isNotEmpty()) {
            serverListAdapter.setData(list)
        }
    }
}


