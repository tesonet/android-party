package com.giedrius.androidparty.task.dependency

import android.content.Context
import android.content.SharedPreferences
import com.giedrius.androidparty.task.App
import com.giedrius.androidparty.task.server.login.LoginClient
import com.giedrius.androidparty.task.server.login.LoginClientImplementation
import com.giedrius.androidparty.task.data.Repository
import com.giedrius.androidparty.task.data.RepositoryImplementation
import com.giedrius.androidparty.task.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides fun provideRepository(loginClient: LoginClient, sharedPreferences: SharedPreferences): Repository = RepositoryImplementation.getInstance(loginClient, sharedPreferences)
    @Provides fun provideLoginClient(): LoginClient = LoginClientImplementation()
    @Provides @Singleton fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    @Provides @Singleton fun provideContext(app: App): Context = app.applicationContext
}