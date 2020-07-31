package com.demo.androidparty.di;

import com.demo.androidparty.di.annotations.DaggerScope;
import com.demo.androidparty.di.module.ServersListFragmentModule;
import com.demo.androidparty.ui.list.ServersListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      MainActivityModule_ProvideServersListFragment$app_debug.ServersListFragmentSubcomponent.class
)
public abstract class MainActivityModule_ProvideServersListFragment$app_debug {
  private MainActivityModule_ProvideServersListFragment$app_debug() {}

  @Binds
  @IntoMap
  @ClassKey(ServersListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ServersListFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = ServersListFragmentModule.class)
  @DaggerScope(ServersListFragment.class)
  public interface ServersListFragmentSubcomponent extends AndroidInjector<ServersListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ServersListFragment> {}
  }
}
