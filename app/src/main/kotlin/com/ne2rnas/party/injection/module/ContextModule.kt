package com.ne2rnas.party.injection.module

import android.app.Application
import android.content.Context
import com.ne2rnas.party.base.BaseView
import dagger.Module
import dagger.Provides

@Module
@Suppress("unused")
object ContextModule {

    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView): Context {
        return baseView.getContext()
    }

    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): Application {
        return context.applicationContext as Application
    }
}
