package com.tesonet.testio.ui.serverslist

import android.content.Intent
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.tesonet.testio.R
import com.tesonet.testio.base.BaseActivity
import com.tesonet.testio.base.Resource
import com.tesonet.testio.data.local.entity.Server
import com.tesonet.testio.ui.onboarding.OnboardingActivity
import kotlinx.android.synthetic.main.activity_serverslist.*


class ServersListActivity: BaseActivity<ServersListViewModel>() {

    private val listAdapter = ServersListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serverslist)

        ViewCompat.setElevation(header, resources.getDimension(R.dimen.servers_header_shadow))

        servers_list.adapter = listAdapter
        servers_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.getServers().observe(this::getLifecycle, this::updateList)

        logout.setOnClickListener {
            finish()
            viewModel.logout()
            startActivity(Intent(this, OnboardingActivity::class.java))
        }
    }

    private fun updateList(resource: Resource<List<Server>>) {
        if (resource.status == Resource.Status.SUCCESS) {
            listAdapter.servers = resource.data!!
            listAdapter.notifyDataSetChanged()
        }
    }
}