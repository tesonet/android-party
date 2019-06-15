package com.mariussavickas.android_party.serverList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariussavickas.android_party.R
import com.mariussavickas.android_party.RootApplication

class ServerListActivity : AppCompatActivity() {

    private lateinit var serverInfoAdapter: ServerInfoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_sever_list)

        val rvServerList = findViewById<RecyclerView>(R.id.rv_server_list)
        rvServerList.layoutManager = LinearLayoutManager(this)
        serverInfoAdapter = ServerInfoRecyclerViewAdapter()
        rvServerList.adapter = serverInfoAdapter
    }

    override fun onStart() {
        super.onStart()

        RootApplication.appDatabase.serverInfoDao().getServerInfoAll()
            .subscribe { serverInfoList ->
                serverInfoAdapter.data = serverInfoList
            }
    }
}
