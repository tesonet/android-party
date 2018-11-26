package com.tesonet.testio.ui

import com.tesonet.testio.ui.loading.LoadingFragment
import com.tesonet.testio.ui.login.LoginFragment
import com.tesonet.testio.ui.onboarding.OnboardingActivity
import com.tesonet.testio.ui.serverslist.ServersListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun contributeOnboardingActivity(): OnboardingActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeLoadingFragment(): LoadingFragment

    @ContributesAndroidInjector
    abstract fun contributeServersListActivity(): ServersListActivity
}