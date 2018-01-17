package lajevski.radoslav.tesonetparty.di;

import dagger.Component;
import lajevski.radoslav.tesonetparty.ui.activities.LoginActivity;
import lajevski.radoslav.tesonetparty.ui.fragments.servers.ServersFragment;
import lajevski.radoslav.tesonetparty.ui.fragments.login.LoginFragment;

/**
 * Created by Radoslav on 1/16/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LoginActivity activity);

    void inject(LoginFragment fragment);

    void inject(ServersFragment fragment);
}
