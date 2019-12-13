package com.giedrius.androidparty.task

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.giedrius.androidparty.task.dependency.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var activity: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragment: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activity
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragment
}