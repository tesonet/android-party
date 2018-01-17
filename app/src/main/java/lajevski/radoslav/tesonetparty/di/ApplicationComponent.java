package lajevski.radoslav.tesonetparty.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import lajevski.radoslav.tesonetparty.App;
import lajevski.radoslav.tesonetparty.data.DataManager;
import lajevski.radoslav.tesonetparty.data.network.NetworkModule;

/**
 * Created by Radoslav on 1/16/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}