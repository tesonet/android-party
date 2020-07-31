package com.demo.androidparty.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.demo.androidparty.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.token.observe(this, Observer { token ->
            setNavController(token)
        })
    }

    private fun setNavController(token: String?) {
        val navController = findNavController(R.id.mainContainer)
        val graph = navController.navInflater.inflate(R.navigation.navigation_graph).apply {
            startDestination = getStartDirection(token)
        }
        navController.graph = graph
    }

    private fun getStartDirection(token: String?): Int =
        if (token == null) {
            R.id.loginFragment
        } else {
            R.id.serversListFragment
        }
}