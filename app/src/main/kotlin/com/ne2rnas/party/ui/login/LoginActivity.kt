package com.ne2rnas.party.ui.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.ne2rnas.party.R
import com.ne2rnas.party.base.BaseActivity
import com.ne2rnas.party.databinding.ActivityLoginBinding
import com.ne2rnas.party.ui.servers.ServersActivity

class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        presenter.onViewCreated()
        hideLoading()
        initLoginListeners()
    }

    private fun initLoginListeners() {
        binding.loginButtonLogin.setOnClickListener {
            onLoginClick()
        }
        binding.loginInputEditPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onLoginClick()
                true
            } else {
                false
            }
        }
    }

    private fun onLoginClick() {
        showLoading()
        presenter.onServerLoginClick(
                binding.loginInputEditEmail.text.toString(),
                binding.loginInputEditPassword.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun openServersActivity() {
        val intent = Intent(this, ServersActivity::class.java)
        startActivity(intent)
        finish()
    }
}
