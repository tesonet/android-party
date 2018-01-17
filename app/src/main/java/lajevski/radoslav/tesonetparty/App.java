package lajevski.radoslav.tesonetparty;

import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

import lajevski.radoslav.tesonetparty.data.DataManager;
import lajevski.radoslav.tesonetparty.data.network.NetworkModule;
import lajevski.radoslav.tesonetparty.di.ApplicationComponent;
import lajevski.radoslav.tesonetparty.di.ApplicationModule;
import lajevski.radoslav.tesonetparty.di.DaggerApplicationComponent;

/**
 * Created by Radoslav on 1/16/2018.
 */

public class App extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(this))
                .build();
        mApplicationComponent.inject(this);

        Log.d("tesolog","The application 'TestIO' has been Created!");
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
