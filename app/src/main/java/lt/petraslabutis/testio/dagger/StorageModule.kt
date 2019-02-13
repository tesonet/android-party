package lt.petraslabutis.testio.dagger

import android.content.Context
import com.securepreferences.SecurePreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    internal fun provideSecurePreferences(context: Context) = SecurePreferences(context)

}