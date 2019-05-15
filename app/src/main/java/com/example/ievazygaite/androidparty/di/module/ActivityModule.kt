package com.example.ievazygaite.androidparty.di.module

import android.app.Activity
import com.example.ievazygaite.androidparty.ui.main.MainContract
import com.example.ievazygaite.androidparty.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }
}