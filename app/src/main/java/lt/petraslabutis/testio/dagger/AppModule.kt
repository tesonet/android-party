package lt.petraslabutis.testio.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

    @Provides
    @Singleton
    internal fun provideApplicationContext() = applicationContext

}