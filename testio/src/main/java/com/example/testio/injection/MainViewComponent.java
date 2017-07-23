package com.example.testio.injection;

import com.example.testio.view.impl.MainActivity;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainViewModule.class)
public interface MainViewComponent {
  void inject(MainActivity activity);
}