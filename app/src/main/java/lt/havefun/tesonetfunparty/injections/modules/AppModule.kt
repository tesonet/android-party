package lt.havefun.tesonetfunparty.injections.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import lt.havefun.tesonetfunparty.App
import lt.havefun.tesonetfunparty.annotations.ApplicationQualifier
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    @ApplicationQualifier
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    @ApplicationQualifier
    fun provideApplicationContext(): Context = app

    @Provides
    @Singleton
    fun provideEventBus(): EventBus = EventBus.getDefault()
}
