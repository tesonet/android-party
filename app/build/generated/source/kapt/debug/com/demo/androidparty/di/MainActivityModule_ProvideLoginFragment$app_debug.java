package com.demo.androidparty.di;

import com.demo.androidparty.di.annotations.DaggerScope;
import com.demo.androidparty.di.module.LoginFragmentModule;
import com.demo.androidparty.ui.login.LoginFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents = MainActivityModule_ProvideLoginFragment$app_debug.LoginFragmentSubcomponent.class
)
public abstract class MainActivityModule_ProvideLoginFragment$app_debug {
  private MainActivityModule_ProvideLoginFragment$app_debug() {}

  @Binds
  @IntoMap
  @ClassKey(LoginFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      LoginFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = LoginFragmentModule.class)
  @DaggerScope(LoginFragment.class)
  public interface LoginFragmentSubcomponent extends AndroidInjector<LoginFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<LoginFragment> {}
  }
}
