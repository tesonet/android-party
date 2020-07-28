package android.example.myapplication.ServersList

import android.content.Context
import android.example.myapplication.R
import android.example.myapplication.ServersList.state.ServersListStateEvent
import android.example.myapplication.model.Server
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_servers_list.*

class ServersListActivity : AppCompatActivity() {

    private val serverAdapter=
        ServerListAdapter(this)

    private val viewModel: ServersListViewModel by lazy {
        ViewModelProviders.of(this).get(ServersListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servers_list)
        setSupportActionBar(viewToolbarView as androidx.appcompat.widget.Toolbar)
        recycler_view.layoutManager=LinearLayoutManager(this)
        recycler_view.adapter=serverAdapter
        // val serverEntityList: List<Server> =listOf(Server("one", 1000), Server("two", 2000))
        //  serverAdapter.setList(serverEntityList)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        subscribeObservers()
        triggerGetServersEvent()
    }

    private fun subscribeObservers() {
        viewModel.dataState("f9731b590611a5a9377fbd02f247fcdf")

            .observe(this, Observer { dataState ->
                println("DEBUG: DataState: $dataState")
                dataState.data?.let { serversListState ->
                    //handle data
                    serversListState.servers?.let { servers ->
                        serverAdapter.notifyDataSetChanged()
                        // set servers data
                        println("DEBUG: Setting servers: $servers")
                        viewModel.setServersListData(servers)
                    }
                }
            })

        viewModel.viewState.observe(this, Observer { viewState ->
            viewState.servers?.let { servers ->
                // set servers to RecyclerView
                serverAdapter.setList(servers)
                println("DEBUG: Setting blog posts to RecyclerView: ${viewState.servers}")
            }
        })
    }

    private fun triggerGetServersEvent() {
        viewModel.setStateEvent(ServersListStateEvent.GetServersEvent())
    }
}