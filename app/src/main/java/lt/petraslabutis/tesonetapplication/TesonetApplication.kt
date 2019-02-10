package lt.petraslabutis.tesonetapplication

import android.app.Application
import lt.petraslabutis.tesonetapplication.dagger.ApiModule
import lt.petraslabutis.tesonetapplication.dagger.ApplicationComponent
import lt.petraslabutis.tesonetapplication.dagger.DaggerApplicationComponent
import lt.petraslabutis.tesonetapplication.dagger.StorageModule
import lt.petraslabutis.tesonetapplication.extensions.scheduleNetworkCall
import lt.petraslabutis.tesonetapplication.managers.AuthenticationManager
import lt.petraslabutis.tesonetapplication.managers.ServerDataManager
import javax.inject.Inject

class TesonetApplication: Application() {

    companion object {
        @JvmStatic lateinit var applicationComponent: ApplicationComponent
    }

    @Inject lateinit var authenticationManager: AuthenticationManager
    @Inject lateinit var serverDataManager: ServerDataManager

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .apiModule(ApiModule())
            .storageModule(StorageModule(applicationContext))
            .build().also {
                it.inject(this)
            }

        //For testing purposes
        authenticationManager
            .login("tesonet", "partyanimal")
            .scheduleNetworkCall()
            .subscribe {
                println(it)
            }

        if (authenticationManager.isAuthenticated()) {
            serverDataManager
                .getServerData()
                .scheduleNetworkCall()
                .subscribe {
                    println(it)
                }
        }
    }
}