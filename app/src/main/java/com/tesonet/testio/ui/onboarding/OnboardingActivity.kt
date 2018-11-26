package com.tesonet.testio.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tesonet.testio.R
import com.tesonet.testio.base.BaseActivity
import com.tesonet.testio.base.Resource
import com.tesonet.testio.data.local.entity.Credentials
import com.tesonet.testio.ui.loading.LoadingFragment
import com.tesonet.testio.ui.login.LoginFragment


class OnboardingActivity : BaseActivity<OnboardingViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        viewModel.getCredentials().observe(this::getLifecycle, this::showFragment)
    }

    private fun showFragment(resource: Resource<Credentials?>) {
        val credentials = resource.data
        val fragment: Fragment = if (credentials != null) LoadingFragment() else LoginFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}