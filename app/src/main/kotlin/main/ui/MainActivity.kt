package main.ui

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.k4dima.party.R
import com.k4dima.party.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import login.ui.LoginActivity
import main.presentation.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val model by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.model = model
        binding.servers
            .addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        model.login.observe(this) {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        var snackbar: Snackbar? = null
        model.error.observe(this) { error ->
            snackbar?.dismiss()
            snackbar = error
                ?.let { Snackbar.make(binding.coordinatorLayout, it, Snackbar.LENGTH_INDEFINITE) }
                ?.also { it.show() }
        }
        binding.swipe.setOnRefreshListener { model.load() }
        val appName = getString(R.string.app_name).toSpannable()
        val length = appName.length
        val colorSecondary = ContextCompat.getColor(this, R.color.logo)
        val colorPrimary = ContextCompat.getColor(this, R.color.color_secondary)
        appName[0 until length] = ForegroundColorSpan(colorSecondary)
        appName[length - 1..length] = ForegroundColorSpan(colorPrimary)
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
            model.loginScreen()
            true
        } else {
            false
        }
}