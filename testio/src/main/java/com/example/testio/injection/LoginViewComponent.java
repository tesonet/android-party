package com.example.testio.injection;

import com.example.testio.view.impl.LoginActivity;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginViewModule.class)
public interface LoginViewComponent {
  void inject(LoginActivity activity);
}