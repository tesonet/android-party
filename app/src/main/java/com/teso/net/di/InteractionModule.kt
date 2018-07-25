package com.teso.net.di

import android.content.Context
import com.teso.net.data_flow.IUserStorage
import com.teso.net.data_flow.database.LocalDatabase
import com.teso.net.data_flow.interactions.*
import com.teso.net.data_flow.network.Api
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractionModule {

    @Provides
    @Singleton
    fun provideInteract(api: Api, pref: IUserStorage, context: Context): ITokenInteractor {
        return TokenInteractor(api, pref, context)
    }

    @Provides
    @Singleton
    fun provideLoginInteractor(pref: IUserStorage): ILoginInteractor = LoginInteractor(pref)

    @Provides
    @Singleton
    fun provideSitesInteractor(api: Api, db: LocalDatabase): IServerInteractor = ServerInteractor(api, db)

}