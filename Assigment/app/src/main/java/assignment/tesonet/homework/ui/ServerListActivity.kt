package assignment.tesonet.homework.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import assignment.tesonet.homework.App
import assignment.tesonet.homework.R
import assignment.tesonet.homework.ServerResponse
import assignment.tesonet.homework.adapter.ServersAdapter
import assignment.tesonet.homework.databinding.ActivityServerListBinding
import assignment.tesonet.homework.ui.viewmodel.ServerActivityViewModel
import assignment.tesonet.homework.ui.factory.ServerActivityModelFactory
import kotlinx.android.synthetic.main.activity_server_list.*
import android.view.animation.AccelerateInterpolator
import assignment.tesonet.homework.animateView
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.view.View


class ServerListActivity : AppCompatActivity() {

    private lateinit var viewModel: ServerActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this,
                ServerActivityModelFactory(application as App))
                .get(ServerActivityViewModel::class.java)

        val binding: ActivityServerListBinding = DataBindingUtil.setContentView(this, R.layout.activity_server_list)
        binding.apply {
            serverViewModel = viewModel
            setLifecycleOwner(this@ServerListActivity)
        }

        viewModel.askServerForServerList()

        viewModel.loginResponse.observe(this, Observer {
            when (it) {
                ServerResponse.SUCCESS -> {
                    viewModel.dataList?.let {
                        val interpolator = AccelerateInterpolator()
                        progressBar.animateView().interpolator = interpolator
                        infoLabel.animateView().interpolator = interpolator
                        backgroundImage.animateView().setInterpolator(interpolator).withEndAction {
                            val itemDecorator = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
                            itemDecorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider)!!)

                            serverRecyclerView.addItemDecoration(itemDecorator)
                            serverRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                            serverRecyclerView.adapter = ServersAdapter(applicationContext, it)
                        }
                    }
                }
                ServerResponse.ERROR -> {
                    Toast.makeText(applicationContext, getString(R.string.response_error), Toast.LENGTH_SHORT).show()
                }
                ServerResponse.NO_INTERNET -> {
                    Toast.makeText(applicationContext, getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    @Suppress("UNUSED_PARAMETER")
    fun logout(view: View) {
        viewModel.logout()
        startActivity(LoginActivity.newIntent(applicationContext))
        finish()
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, ServerListActivity::class.java)
        }
    }
}