package lt.havefun.tesonetfunparty.injections.modules

import dagger.Module
import dagger.Provides
import lt.havefun.tesonetfunparty.data.db.CachedServersListRepository
import lt.havefun.tesonetfunparty.data.MainRepository
import lt.havefun.tesonetfunparty.network.Api
import lt.havefun.tesonetfunparty.ui.main.ServersViewModel
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun providesMainViewModelFactory(
        mainRepository: MainRepository,
        cachedServersListRepository: CachedServersListRepository
    ): ServersViewModel.Factory {
        return ServersViewModel.Factory(
            mainRepository = mainRepository,
            cachedServersListRepository = cachedServersListRepository
        )
    }

    @Provides
    @Singleton
    fun providesMainRepository(api: Api): MainRepository {
        return MainRepository(api)
    }
}