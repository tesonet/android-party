package feature.main.data.local.db.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import feature.main.data.local.db.ServersDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun provideMessageDao(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, ServersDatabase::class.java, "database")
        .build()
        .serversDao()
}