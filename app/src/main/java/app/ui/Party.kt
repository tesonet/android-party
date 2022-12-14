package app.ui

import android.app.Application
import android.os.StrictMode
import com.k4dima.party.BuildConfig.DEBUG
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Party : Application() {
    init {
        if (DEBUG) {
            StrictMode.enableDefaults()
            Timber.plant(Timber.DebugTree())
        }
    }
}