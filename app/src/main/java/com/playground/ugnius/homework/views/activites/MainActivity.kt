package com.playground.ugnius.homework.views.activites

import android.graphics.Color
import android.os.Bundle
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import com.playground.ugnius.homework.R
import com.playground.ugnius.homework.global.App
import android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.playground.ugnius.homework.model.ServersRepository
import com.playground.ugnius.homework.views.fragments.LoginFragment
import com.playground.ugnius.homework.views.fragments.ServersFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var serversRepository: ServersRepository
    var idlingResource = CountingIdlingResource("Network Call")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as? App)?.mainComponent?.inject(this)
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LAYOUT_STABLE
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val servers = serversRepository.getServers()
            val fragment = if (servers.isEmpty()) LoginFragment() else ServersFragment()
            supportFragmentManager.beginTransaction().add(R.id.content, fragment).commit()
        }
    }

    fun navigate(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, fragment)
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .commit()
    }

}