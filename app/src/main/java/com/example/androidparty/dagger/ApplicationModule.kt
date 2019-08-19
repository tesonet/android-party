package com.example.androidparty.dagger

import android.content.Context
import android.content.SharedPreferences
import com.example.androidparty.AndroidPartyApplication
import com.example.androidparty.database.ServersDB
import com.example.androidparty.networking.AuthClient
import com.example.androidparty.networking.AuthClientImpl
import com.example.androidparty.repository.Repository
import com.example.androidparty.repository.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideDatabase(context: Context): ServersDB = ServersDB.getInstance(context)


    @Provides
    fun provideRepository(authClient: AuthClient, prefs: SharedPreferences, serversDB: ServersDB): Repository = RepositoryImpl.getInstance(authClient, prefs, serversDB)

    @Provides
    fun provideServersDatabase(context: Context): ServersDB = ServersDB.getInstance(context)

    @Provides
    fun provideAuthClient(): AuthClient = AuthClientImpl()

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences("LogIn",
        Context.MODE_PRIVATE
    )

    @Provides
    fun provideContext(app: AndroidPartyApplication): Context = app.applicationContext
}