package app.ui

import android.os.StrictMode
import com.k4dima.party.BuildConfig.DEBUG
import app.ui.di.DaggerAppComponent
import dagger.android.support.DaggerApplication
import timber.log.Timber

class Party : DaggerApplication() {
    override fun applicationInjector() =
        DaggerAppComponent.builder().context(this).build()

    init {
        if (DEBUG) {
            StrictMode.enableDefaults()
            Timber.plant(Timber.DebugTree())
        }
    }
}