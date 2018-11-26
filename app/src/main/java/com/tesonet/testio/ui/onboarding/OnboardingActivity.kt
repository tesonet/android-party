package com.tesonet.testio.ui.onboarding

import android.os.Bundle
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
            goToFragment(resource.data)
        } else if (resource.status == Resource.Status.ERROR) {
            toast(R.string.unexpected_error)
        }
    }

    private fun goToFragment(credentials: Credentials?) {
        val fragment = if (credentials != null) LoadingFragment() else LoginFragment()
        val transaction = supportFragmentManager.beginTransaction()
        // Do not animate initial fragment draw
        if (supportFragmentManager.fragments.size > 0) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        transaction.replace(R.id.container, fragment)
                .commit()
    }
}