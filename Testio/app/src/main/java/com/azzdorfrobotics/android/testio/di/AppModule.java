package com.azzdorfrobotics.android.testio.di;

import android.content.Context;
import com.azzdorfrobotics.android.testio.App;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */
@Singleton @Module public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides Context provideApplicationContext() {
        return application;
    }

    @Provides App provideApplication() {
        return application;
    }
}