package lt.havefun.tesonetfunparty.injections.modules

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import lt.havefun.tesonetfunparty.MainActivityViewModel
import lt.havefun.tesonetfunparty.annotations.ActivityQualifier
import lt.havefun.tesonetfunparty.data.IPreferencesManager
import lt.havefun.tesonetfunparty.data.db.CachedServersListRepository
import javax.inject.Singleton

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @Singleton
    @ActivityQualifier
    internal fun provideActivityContext(): Context = activity

    @Provides
    @Singleton
    internal fun provideActivity(): Activity = activity

    @Provides
    @Singleton
    fun providesActivityMainViewModelFactory(
        preferencesManager: IPreferencesManager,
        cachedServersListRepository: CachedServersListRepository
    ): MainActivityViewModel.Factory {
        return MainActivityViewModel.Factory(
            preferencesManager = preferencesManager,
            cachedServersListRepository = cachedServersListRepository
        )
    }
}