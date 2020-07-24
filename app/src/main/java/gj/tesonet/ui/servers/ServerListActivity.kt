package gj.tesonet.ui.servers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import gj.tesonet.R
import gj.tesonet.ui.AppActivity
import gj.tesonet.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_servers_list.*

class ServerListActivity : AppActivity() {

    private val adapter: ServerListAdapter by lazy {
        ServerListAdapter()
    }

    private val model: ServerListViewModel by lazy {
        ViewModelProvider(this).get(ServerListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_servers_list)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        model.servers.observe(this) { servers ->
            adapter.list = servers

            if (servers != null) {
                background.visibility = View.GONE
                list.visibility = View.VISIBLE
                header.visibility = View.VISIBLE
            }
        }

        model.message.observe(this) {
            show(it)
        }
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ServerListActivity::class.java)

            context.startActivity(intent)
        }
    }
}
