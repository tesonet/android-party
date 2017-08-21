package lt.marius.testio.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_server_list.*
import lt.marius.testio.LogoutEvent
import lt.marius.testio.R
import lt.marius.testio.adapter.DelegateAdapter
import lt.marius.testio.adapter.ServerDisplayableItem
import lt.marius.testio.model.Server
import lt.marius.testio.viewModel.ServersViewModel
import org.greenrobot.eventbus.EventBus

/**
 * Created by marius on 17.8.21.
 */
class ServerListFragment : BaseFragment() {
	override val layoutId = R.layout.fragment_server_list

	val adapter = DelegateAdapter()
	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		logoutButton.setOnClickListener {
			EventBus.getDefault().post(LogoutEvent())
		}

		serverRecycler.adapter = adapter
		serverRecycler.layoutManager = LinearLayoutManager(context)

		ViewModelProviders
				.of(activity)
				.get(ServersViewModel::class.java)
				.servers
				.observe(this, Observer {
					observeServerList(it)
				})
	}


	private fun observeServerList(it: List<Server>?) {
		adapter.clear()
		it?.let {
			adapter.addAll(it.map { ServerDisplayableItem(it) })
		}
	}

}

