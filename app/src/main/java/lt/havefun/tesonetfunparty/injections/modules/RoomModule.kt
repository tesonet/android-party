package lt.havefun.tesonetfunparty.injections.modules

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import lt.havefun.tesonetfunparty.data.db.CachedServersListRepository
import lt.havefun.tesonetfunparty.data.db.ServersDatabase
import lt.havefun.tesonetfunparty.data.db.ServersDao
import javax.inject.Singleton

@Module
class RoomModule(var app: Application) {

    @Singleton
    @Provides
    fun providesDatabase(): ServersDatabase = Room
        .databaseBuilder(app, ServersDatabase::class.java, "tesonet.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providesCachedServersDao(): ServersDao {
        return providesDatabase().getCachedServers()
    }

    @Singleton
    @Provides
    fun providesCachedServersRepository(): CachedServersListRepository {
        return CachedServersListRepository(
            providesCachedServersDao()
        )
    }
}