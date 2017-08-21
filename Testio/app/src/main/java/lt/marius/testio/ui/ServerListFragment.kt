package lt.marius.testio.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_server_list.*
import lt.marius.testio.LogoutEvent
import lt.marius.testio.R
import lt.marius.testio.model.Server
import lt.marius.testio.viewModel.ServersViewModel
import org.greenrobot.eventbus.EventBus

/**
 * Created by marius on 17.8.21.
 */
class ServerListFragment : BaseFragment() {
	override val layoutId = R.layout.fragment_server_list

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		logoutButton.setOnClickListener {
			EventBus.getDefault().post(LogoutEvent())
		}

		ViewModelProviders
				.of(activity)
				.get(ServersViewModel::class.java)
				.servers
				.observe(this, Observer {
					observeServerList(it)
				})
	}

	private fun observeServerList(it: List<Server>?) {
		it?.let {

		}
	}

}

