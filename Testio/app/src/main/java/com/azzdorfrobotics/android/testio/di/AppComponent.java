package com.azzdorfrobotics.android.testio.di;

import com.azzdorfrobotics.android.testio.App;
import com.azzdorfrobotics.android.testio.network.NetworkModule;
import com.azzdorfrobotics.android.testio.preferences.AppPreferences;
import com.azzdorfrobotics.android.testio.preferences.PreferenceModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */
@Singleton
@Component(modules = {
    AppModule.class,
    ResourceModule.class,
    NetworkModule.class,
    PreferenceModule.class,
    RealmModule.class})
public interface AppComponent {

    void inject(App app);

    ServerComponent serverComponent();

    AppPreferences provideAppPreferences();
}
