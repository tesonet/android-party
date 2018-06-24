package com.ne2rnas.party.injection.module

import android.content.Context
import com.ne2rnas.party.utils.AppPrefsHelper
import com.ne2rnas.party.utils.SharedPrefsHelper
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
@Suppress("unused")
object SharedPrefsModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideAppPrefsHelper(context: Context): AppPrefsHelper {
        return AppPrefsHelper(context)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideSharedPrefsHelper(appPrefsHelper: AppPrefsHelper):
            SharedPrefsHelper = appPrefsHelper
}
