package app.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Singleton
    @Provides
    fun preferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}