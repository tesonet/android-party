package com.jonastiskus.testio.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.jonastiskus.testio.TestioApplication
import com.jonastiskus.testio.model.ServerGsonModel
import com.jonastiskus.testio.viewmodel.ServerViewModel
import android.util.Log
import com.jonastiskus.testio.R
import com.jonastiskus.testio.repository.room.entity.ServerEntity


class LoadingActivity : AppCompatActivity() {

    companion object {
        const val JSON_EXTRA = "JSON_EXTRA"
    }

    private lateinit var viewModel: ServerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val testioApplication = application as TestioApplication
        val serviceProvider = testioApplication.getServiceProvider()
        val authToken = serviceProvider.getAuthService().getAuthToken()

        val serverViewModel =
            ServerViewModel.ServiewViewModelFactory(serviceProvider, authToken)

        viewModel = ViewModelProviders.of(this, serverViewModel)[ServerViewModel::class.java]

        viewModel.listLiveData.observe(this, Observer<List<ServerGsonModel>> {
            if (it != null) {
                val gson = Gson()
                val json = gson.toJson(it)
                startServerListActivity(json)
            }
        })

        viewModel.cachedDataListLiveData.observe(this, Observer<ServerEntity> {
            if (it != null) {
                Log.d("cached value:", it.json)
            }
        })

        viewModel.load()

    }

    private fun startServerListActivity(extra: String) {
        val intent = Intent(this, ServerActivity::class.java)
        val bundle = Bundle()
        bundle.putString(JSON_EXTRA, extra)
        intent.putExtras(bundle)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}
