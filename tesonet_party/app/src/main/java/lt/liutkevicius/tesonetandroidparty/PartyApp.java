package lt.liutkevicius.tesonetandroidparty;

import android.app.Application;
import lt.liutkevicius.tesonetandroidparty.di.components.DaggerIApplicationComponent;
import lt.liutkevicius.tesonetandroidparty.di.components.IApplicationComponent;
import lt.liutkevicius.tesonetandroidparty.di.module.ApplicationModule;


public class PartyApp extends Application {

    private static IApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerIApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build(); // fixme
    }

    public static IApplicationComponent getAppComponent() {
        return applicationComponent;
    }
}
