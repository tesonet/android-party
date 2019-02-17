package lt.petraslabutis.testio

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import lt.petraslabutis.testio.dagger.*
class TestioApplication: Application() {

    companion object {
        @JvmStatic
        lateinit var applicationComponent: ApplicationComponent
        private const val CURRENT_REALM_SCHEMA_VERSION = 1L
    }

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmConfiguration = RealmConfiguration
            .Builder()
            .schemaVersion(CURRENT_REALM_SCHEMA_VERSION)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfiguration)

        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(applicationContext))
            .apiModule(ApiModule())
            .storageModule(StorageModule())
            .build()
    }
}