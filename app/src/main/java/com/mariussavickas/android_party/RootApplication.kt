package com.mariussavickas.android_party

import android.app.Application
import androidx.room.Room
import com.codecave.outmatch.shared.Settings
import com.codecave.outmatch.shared.server.ApiService
import com.mariussavickas.android_party.persistance.AppDatabase
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class RootApplication : Application() {
    lateinit var appDatabase: AppDatabase private set
    lateinit var repository: Repository private set

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "partyDb"
        ).build()

        val httpService = OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
        val apiService = ApiService(httpService, Settings.API_ENDPOINT_BASE_V1)
        repository = Repository(apiService, appDatabase)
    }
}