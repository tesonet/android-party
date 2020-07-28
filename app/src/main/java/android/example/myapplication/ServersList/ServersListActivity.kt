package android.example.myapplication.ServersList

import android.example.myapplication.R
import android.example.myapplication.model.Server
import android.example.myapplication.util.ApiEmptyResponse
import android.example.myapplication.util.ApiErrorResponse
import android.example.myapplication.util.ApiSuccessResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        val serverEntityList: List<Server> =listOf(Server("one", 1000), Server("two", 2000))
        //  serverAdapter.setList(serverEntityList)
        //serverAdapter.notifyDataSetChanged()
        /*
        viewModel.testApiResponse("f9731b590611a5a9377fbd02f247fcdf").observe(this, Observer { response ->
            when (response) {
                is ApiSuccessResponse -> {
                   println("DEBUG: LOGIN RESPONSE: ${response.body}")
                }
                is ApiErrorResponse -> {
                    println("DEBUG: LOGIN RESPONSE: ${response.errorMessage}")
                }
                is ApiEmptyResponse -> {
                   println("DEBUG: LOGIN RESPONSE: Empty Response")
                }
            }
        }) */
        //subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            println("DEBUG: DataState: $dataState")
            dataState.data?.let { serversListState ->
                //handle data
                serversListState.servers?.let { servers ->
                    // set servers data
                    println("DEBUG: Setting servers: $servers")
                    viewModel.setServersListData(servers)
                }
                //handle errors:
                dataState.message?.let {
                    println("DEBUG: MESSAGE: $it")
                }
                //handle loading:
                println("DEBUG: LOADING: ${dataState.loading}")
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
}