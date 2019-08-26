package com.example.androidparty

import android.app.Activity
import android.app.Application
import com.example.androidparty.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class AndroidPartyApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {


    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector
    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = fragmentDispatchingAndroidInjector
}