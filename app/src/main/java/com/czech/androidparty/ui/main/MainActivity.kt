package com.czech.androidparty.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.postDelayed
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.czech.androidparty.R
import com.czech.androidparty.databinding.ActivityMainBinding
import com.czech.androidparty.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var appBarConfig: AppBarConfiguration

    private var backPressedOnce = false

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        setSupportActionBar(binding.toolbar)
        setUpNavigation()

    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHostFragment.navController

        appBarConfig = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.listFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfig)

        if (viewModel.isLoggedIn) {
            navController.navigate(R.id.listFragment)
        } else {
            navController.navigate(R.id.loginFragment)
        }

        navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id) {
                R.id.loginFragment -> {
                    binding.toolbar.hide()
                }
                R.id.listFragment -> {
                    binding.toolbar.show()
                    binding.toolbar.navigationIcon = null
                    binding.logoutBtn.setOnClickListener {
                        viewModel.apply {
                            deleteToken()
                            deleteData()
                            launchFragment(R.id.loginFragment, navController)
                        }
                    }
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfig)
    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount == 0) {
            if (backPressedOnce) {
                super.onBackPressed()
                return
            }
            backPressedOnce = true
            this.showShortToast("Press BACK again to exit")

            Handler().postDelayed(5000) {
                backPressedOnce = false
            }
        } else {
            super.onBackPressed()
        }
    }
}