package lt.bulevicius.tessonetapp.app;

import dagger.Component;
import lt.bulevicius.tessonetapp.ui.MainActivity;

@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface TesApplicationComponent {

    void inject(MainActivity mainActivity);

}
