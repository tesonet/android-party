package com.tesonet.testio.util

import android.content.Context
import com.tesonet.testio.util.asynclaucher.AsyncLauncher
import com.tesonet.testio.util.asynclaucher.AsyncLauncherImpl
import com.tesonet.testio.util.networkavailability.NetworkAvailability
import com.tesonet.testio.util.networkavailability.NetworkAvailabilityImpl
import dagger.Module
import dagger.Provides

@Module
class UtilModule {

    @Provides
    fun provideNetworkAvailability(context: Context): NetworkAvailability
            = NetworkAvailabilityImpl(context)

    @Provides
    fun provideAsyncLauncher(): AsyncLauncher
            = AsyncLauncherImpl()
}