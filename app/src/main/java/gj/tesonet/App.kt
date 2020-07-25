package gj.tesonet

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.VisibleForTesting
import gj.tesonet.backend.Backend
import gj.tesonet.data.Data
import gj.tesonet.data.model.User
import timber.log.Timber

class App: Application() {

    val data: Data by lazy {
        Data.create(this)
    }

    var user: User? = if (BuildConfig.DEBUG) User("tesonet", "partyanimal") else null

    // fast way to mock network connectivity
    private var _online: Boolean? = null

    var online: Boolean
        @VisibleForTesting
        set(value) {
            _online = value
        }
        get() = _online
            ?: (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)
                ?.activeNetworkInfo?.isConnected
            ?: false

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}