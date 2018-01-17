package lajevski.radoslav.tesonetparty.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lajevski.radoslav.tesonetparty.data.AppDataManager;
import lajevski.radoslav.tesonetparty.data.DataManager;
import lajevski.radoslav.tesonetparty.data.preferences.AppPreferencesHelper;
import lajevski.radoslav.tesonetparty.data.preferences.PreferencesHelper;

/**
 * Created by Radoslav on 1/16/2018.
 */

    @Module
    public class ApplicationModule {

        private final Application mApplication;

        public ApplicationModule(Application application) {
            mApplication = application;
        }

        @Provides
        @ApplicationContext
        Context provideContext() {
            return mApplication;
        }

        @Provides
        Application provideApplication() {
            return mApplication;
        }

        @Provides
        @PreferencesInfo
        String providePreferenceName() {
            return "tesonet_prefs";
        }

        @Provides
        @Singleton
        DataManager provideDataManager(AppDataManager appDataManager) {
            return appDataManager;
        }

        @Provides
        @Singleton
        PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
            return appPreferencesHelper;
        }
    }
