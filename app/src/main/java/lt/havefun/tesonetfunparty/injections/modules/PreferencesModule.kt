package lt.havefun.tesonetfunparty.injections.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import lt.havefun.tesonetfunparty.annotations.ApplicationQualifier
import lt.havefun.tesonetfunparty.annotations.PreferencesQualifier
import lt.havefun.tesonetfunparty.data.IPreferencesManager
import lt.havefun.tesonetfunparty.data.PreferencesManager
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Provides
    @Singleton
    @PreferencesQualifier
    fun providePreferences(@ApplicationQualifier context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(
            context,
            MasterKey.DEFAULT_MASTER_KEY_ALIAS
        )
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            "tesonet",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun providePreferencesManager(@PreferencesQualifier prefs: SharedPreferences): IPreferencesManager =
        PreferencesManager(prefs)
}