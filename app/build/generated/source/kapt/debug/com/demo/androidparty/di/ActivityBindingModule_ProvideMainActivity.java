package com.demo.androidparty.di;

import com.demo.androidparty.di.annotations.DaggerScope;
import com.demo.androidparty.ui.main.MainActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = ActivityBindingModule_ProvideMainActivity.MainActivitySubcomponent.class)
public abstract class ActivityBindingModule_ProvideMainActivity {
  private ActivityBindingModule_ProvideMainActivity() {}

  @Binds
  @IntoMap
  @ClassKey(MainActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MainActivitySubcomponent.Factory builder);

  @Subcomponent(modules = MainActivityModule.class)
  @DaggerScope(MainActivity.class)
  public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MainActivity> {}
  }
}
