package lt.liutkevicius.tesonetandroidparty.di.components;

import dagger.Component;
import lt.liutkevicius.tesonetandroidparty.di.module.ApplicationModule;
import lt.liutkevicius.tesonetandroidparty.di.module.NetworkModule;
import lt.liutkevicius.tesonetandroidparty.storage.SharedPrefs;
import lt.liutkevicius.tesonetandroidparty.ui.MainActivity;
import lt.liutkevicius.tesonetandroidparty.ui.login.LoginViewImpl;
import lt.liutkevicius.tesonetandroidparty.ui.servers.ServersViewImpl;

import javax.inject.Singleton;

@Component(modules = {ApplicationModule.class, NetworkModule.class})
@Singleton
public interface IApplicationComponent {

    SharedPrefs getSharedPrefs();

    void inject(MainActivity mainActivity);

    void inject(LoginViewImpl loginView);

    void inject(ServersViewImpl serversView);

}
