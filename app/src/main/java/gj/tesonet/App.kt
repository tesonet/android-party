package gj.tesonet

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import gj.tesonet.backend.Backend
import gj.tesonet.data.Data
import gj.tesonet.data.model.User
import timber.log.Timber

class App: Application() {

    val data: Data by lazy {
        Data.create(this)
    }

    val backend: Backend by lazy {
        Backend.create()
    }

    var user: User? = if (BuildConfig.DEBUG) User("tesonet", "partyanimal") else null

    private var _online: Boolean? = null

    val online: Boolean
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