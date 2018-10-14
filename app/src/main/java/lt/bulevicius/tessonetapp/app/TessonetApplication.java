package lt.bulevicius.tessonetapp.app;

import android.app.Application;

/**
 * The type Tessonet application.
 */
public class TessonetApplication extends Application {

    private static TesApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerTesApplicationComponent.builder()
                                                 .applicationModule(new ApplicationModule(this))
                                                 .build();
    }

    /**
     * Gets app component.
     *
     * @return the component
     */
    public static TesApplicationComponent getComponent() {
        return component;
    }
}
