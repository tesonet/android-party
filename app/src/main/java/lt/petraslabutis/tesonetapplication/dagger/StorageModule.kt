package lt.petraslabutis.tesonetapplication.dagger

import android.content.Context
import com.securepreferences.SecurePreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    internal fun provideSecurePreferences() = SecurePreferences(applicationContext)

}