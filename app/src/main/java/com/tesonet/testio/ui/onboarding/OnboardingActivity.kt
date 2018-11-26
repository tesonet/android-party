package com.tesonet.testio.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tesonet.testio.R
import com.tesonet.testio.base.BaseActivity
import com.tesonet.testio.base.Resource
import com.tesonet.testio.data.local.entity.Credentials
import com.tesonet.testio.extension.toast
import com.tesonet.testio.ui.loading.LoadingFragment
import com.tesonet.testio.ui.login.LoginFragment


class OnboardingActivity : BaseActivity<OnboardingViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        viewModel.getCredentials().observe(this::getLifecycle, this::showFragment)
    }

    private fun showFragment(resource: Resource<Credentials?>) {
        if (resource.status == Resource.Status.SUCCESS) {
            goToFirstFragment(resource.data)
        } else if (resource.status == Resource.Status.ERROR) {
            toast(R.string.unexpected_error)
        }
    }

    private fun goToFirstFragment(credentials: Credentials?) {
        val fragment: Fragment = if (credentials != null) LoadingFragment() else LoginFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}