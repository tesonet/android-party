package com.mariussavickas.android_party.serverList

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariussavickas.android_party.R
import com.mariussavickas.android_party.RootApplication
import androidx.recyclerview.widget.DividerItemDecoration
import com.mariussavickas.android_party.ApiController
import io.reactivex.android.schedulers.AndroidSchedulers


class ServerListActivity : AppCompatActivity() {

    private lateinit var serverInfoAdapter: ServerInfoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.sever_list)

        val rvServerList = findViewById<RecyclerView>(R.id.rv_server_list)

        val linearLayoutManger = LinearLayoutManager(this)
        rvServerList.layoutManager = linearLayoutManger

        serverInfoAdapter = ServerInfoRecyclerViewAdapter()
        rvServerList.adapter = serverInfoAdapter

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        rvServerList.addItemDecoration(dividerItemDecoration)

        findViewById<ImageView>(R.id.iv_server_list_logout).setOnClickListener {
            ApiController.logout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    this@ServerListActivity.onBackPressed()
            }
        }

    }

    override fun onStart() {
        super.onStart()

        RootApplication.appDatabase.serverInfoDao().getServerInfoAll()
            .subscribe { serverInfoList ->
                serverInfoAdapter.data = serverInfoList
            }
    }
}
