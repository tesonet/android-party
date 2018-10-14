package lt.bulevicius.tessonetapp.app;

import javax.inject.Singleton;

import dagger.Component;
import lt.bulevicius.tessonetapp.ui.MainActivity;
import lt.bulevicius.tessonetapp.ui.login.LoginViewImpl;

@Component(modules = {ApplicationModule.class, NetworkModule.class})
@Singleton
public interface TesApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginViewImpl loginView);
}
