package lt.petraslabutis.testio

import android.app.Application
import io.realm.Realm
import lt.petraslabutis.testio.dagger.*
class TestioApplication: Application() {

    companion object {
        @JvmStatic
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(applicationContext))
            .apiModule(ApiModule())
            .storageModule(StorageModule())
            .build()
    }
}