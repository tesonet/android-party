package com.azzdorfrobotics.android.testio.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

@Module public class PreferenceModule {

    private static final String STORE_FILE_NAME = "general_prefs";

    @Provides @Singleton SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(STORE_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Provides @Singleton AppPreferences provideAppPreferences(SharedPreferences sharedPreferences) {
        return new AppPreferences(sharedPreferences);
    }
}
