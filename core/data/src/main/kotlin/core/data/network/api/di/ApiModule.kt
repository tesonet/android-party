package core.data.network.api.di

import core.data.network.api.TestioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
object ApiModule {
    @Provides
    @ActivityRetainedScoped
    fun testioService(client: OkHttpClient): TestioService = Retrofit.Builder()
        .baseUrl("https://playground.tesonet.lt/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(TestioService::class.java)
}