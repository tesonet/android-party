package lt.petraslabutis.testio

import android.app.Application
import lt.petraslabutis.testio.dagger.*
class TestioApplication: Application() {

    companion object {
        @JvmStatic
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(applicationContext))
            .apiModule(ApiModule())
            .storageModule(StorageModule())
            .build()
    }
}