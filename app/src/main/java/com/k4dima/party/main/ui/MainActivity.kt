package com.k4dima.party.main.ui

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.k4dima.party.main.presentation.MainViewModel
import com.k4dima.party.R
import com.k4dima.party.databinding.ActivityMainBinding
import com.k4dima.party.login.ui.LoginActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)
        val recyclerView = binding.servers
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        model.servers.observe(this, Observer {
            recyclerView.adapter = ServersAdapter(it)
        })
        model.failure.observe(this, Observer {
            startActivity(Intent(this, LoginActivity::class.java))
        })
        val appName = getString(R.string.app_name).toSpannable()
        val length = appName.length
        appName[0 until length] = ForegroundColorSpan(resources.getColor(R.color.color_primary))
        appName[length - 1..length] = ForegroundColorSpan(resources.getColor(R.color.color_secondary))
        appName[0..length] = StyleSpan(Typeface.BOLD)
        appName[0..length] = RelativeSizeSpan(1.5f)
        supportActionBar!!.title = appName
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            if (item.itemId == R.id.logout) {
                model.logout()
                true
            } else {
                false
            }
}