package com.czech.androidparty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.os.postDelayed
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.czech.androidparty.databinding.ActivityMainBinding
import com.czech.androidparty.utils.hide
import com.czech.androidparty.utils.show
import com.czech.androidparty.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var appBarConfig: AppBarConfiguration

    private var backPressedOnce = false

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

        appBarConfig = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.listFragment
            )
        )
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController, appBarConfig)

        navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id) {
                R.id.loginFragment -> {
                    binding.toolbar.hide()
                }
                R.id.listFragment -> {
                    binding.toolbar.show()
                    binding.toolbar.navigationIcon = null
                    binding.logoutBtn.setOnClickListener {

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