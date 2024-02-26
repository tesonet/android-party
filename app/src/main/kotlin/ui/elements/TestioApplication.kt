package ui.elements

import android.app.Application
import com.github.k4dima.testio.BuildConfig.DEBUG
import core.ui.elements.commonApplicationInit
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        commonApplicationInit(DEBUG, this)
    }
}