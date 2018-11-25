package com.tesonet.testio.util

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class UtilModule {

    @Provides
    fun provideNetworkAvailability(context: Context): NetworkAvailability
            = NetworkAvailabilityImpl(context)
}