package com.azzdorfrobotics.android.testio.di;

import com.azzdorfrobotics.android.testio.features.auth.AuthCase;
import com.azzdorfrobotics.android.testio.features.auth.LoginFragment;
import com.azzdorfrobotics.android.testio.features.servers.LoadingFragment;
import com.azzdorfrobotics.android.testio.features.servers.ServerCase;
import com.azzdorfrobotics.android.testio.features.servers.ServerListFragment;
import com.azzdorfrobotics.android.testio.features.shared.MainActivity;
import dagger.Subcomponent;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

@ActivityScope @Subcomponent public interface ServerComponent {

    void inject(MainActivity mainActivity);

    void inject(ServerListFragment serverListFragment);

    void inject(LoadingFragment loginFragment);

    void inject(LoginFragment loginFragment);


    AuthCase provideAuthCase();

    ServerCase provideServerCase();
}
