package login.ui

import android.content.Intent
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.ViewModelProvider
import com.k4dima.party.R
import com.k4dima.party.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import login.presentation.LoginViewModel
import main.ui.MainActivity
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val model by viewModels<LoginViewModel> { viewModelFactory }
        binding.model = model
        listOf(binding.userName, binding.password).forEach {
            it.setOnTouchListener { v, _ ->
                model.error.set(null)
                v.performClick()
                false
            }
        }
        model.main.observe(this) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
        val appName = getString(R.string.app_name).toSpannable()
        val length = appName.length
        appName[length - 1..length] =
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.color_secondary))
        binding.title.text = appName
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}