package com.example.androidparty.ui.server

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidparty.R
import com.example.androidparty.ServerListAdapter
import com.example.androidparty.SharedPreferences
import com.example.androidparty.db.ServerEntity
import com.example.androidparty.utils.openLoginScreen
import kotlinx.android.synthetic.main.server_list_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class ServerActivity : AppCompatActivity(), View.OnClickListener {

    private val serverAdapter = ServerListAdapter(this)

    companion object {
        val SERVERS_LIST = "servers_list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.server_list_layout)
        val toolbar = viewToolbarView as Toolbar
        setSupportActionBar(toolbar)
        toolbar.viewImageViewLogout.setOnClickListener(this)
        viewRecyclerView.layoutManager = LinearLayoutManager(this)
        viewRecyclerView.adapter = serverAdapter
        val serverEntityList: List<ServerEntity> =
            (intent.getSerializableExtra(SERVERS_LIST) as List<*>).filterIsInstance<ServerEntity>()
        serverAdapter.setList(serverEntityList)
        serverAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        when (v.id) {
            viewImageViewLogout.id -> {
                SharedPreferences(applicationContext).removeToken()
                openLoginScreen(this)
                this.finish()
            }
        }
    }
}