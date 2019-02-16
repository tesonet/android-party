package lt.petraslabutis.testio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_server_list.*
import kotlinx.android.synthetic.main.fragment_server_list.view.*
import lt.petraslabutis.testio.R
import lt.petraslabutis.testio.TestioApplication
import lt.petraslabutis.testio.adapters.ServerListAdapter
import lt.petraslabutis.testio.entities.ServerItem
import lt.petraslabutis.testio.extensions.onClick
import lt.petraslabutis.testio.extensions.scheduleNetworkCall
import lt.petraslabutis.testio.extensions.setRightPadding
import lt.petraslabutis.testio.extensions.widthInPx
import lt.petraslabutis.testio.viewmodels.AuthenticationViewModel
import lt.petraslabutis.testio.viewmodels.NavigationViewModel
import lt.petraslabutis.testio.viewmodels.ServerListViewModel
import javax.inject.Inject

class ServerListFragment : BaseFragment() {

    @Inject
    lateinit var serverListViewModel: ServerListViewModel
    @Inject
    lateinit var authenticationViewModel: AuthenticationViewModel
    @Inject
    lateinit var navigationViewModel: NavigationViewModel

    override fun inject() {
        TestioApplication.applicationComponent.inject(this)
    }

    private lateinit var listAdapter: ServerListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_server_list, container, false).apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ServerListAdapter().apply { listAdapter = this }
            }

            swipeLayout.setOnRefreshListener {
                refreshList()
            }
        }

    override fun onStart() {
        super.onStart()
        view.apply {
            logoutView.onClick {
                authenticationViewModel.logout()
                navigationViewModel.replaceTopFragment(LoginFragment())

            }.addTo(disposables)
        }
    }

    override fun onEnterAnimationEnd() {
        refreshList()
    }

    private fun refreshList() {
        serverListViewModel
            .getServerData()
            .scheduleNetworkCall()
            .subscribe {
                val items = it.map {
                    ServerItem(
                        it.name,
                        String.format(
                            resources.getString(R.string.server_list_distance_format),
                            it.distance
                        )
                    )
                }
                val longestWidth = getLongestWidth(items)
                listAdapter.update(items, longestWidth)
                rightText.setRightPadding(longestWidth - rightText.text.toString().widthInPx(rightText))
                swipeLayout.isRefreshing = false
            }.addTo(disposables)
    }

    private fun getLongestWidth(items: List<ServerItem>): Int =
        items
            .map { it.distance }
            .plus(rightText.text.toString())
            .map { it.widthInPx(rightText) }
            .max() ?: 0

}