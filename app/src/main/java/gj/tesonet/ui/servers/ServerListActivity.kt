package gj.tesonet.ui.servers

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import gj.tesonet.R
import gj.tesonet.ui.login.LoginActivity
import gj.tesonet.ui.show
import kotlinx.android.synthetic.main.activity_servers_list.*
import kotlinx.android.synthetic.main.toolbar.view.*

class ServerListActivity : AppCompatActivity() {

    private val adapter: ServerListAdapter by lazy {
        ServerListAdapter()
    }

    private val model: ServerListViewModel by lazy {
        ViewModelProvider(this).get(ServerListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_servers_list)

        toolbar.logout.setOnClickListener {
            model.logout()

            LoginActivity.start(this)
            finish()
        }

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        model.servers.observe(this) { servers ->
            adapter.list = servers

            if (servers != null) {
                listContainer.visibility = View.VISIBLE
                progressContainer.visibility = View.GONE
            }
        }

        model.message.observe(this) { reference ->
            reference.getAndSet(null)?.let { message ->
                show(message)
            }
        }
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ServerListActivity::class.java)

            context.startActivity(intent)
        }
    }
}
