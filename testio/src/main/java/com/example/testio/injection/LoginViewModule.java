package com.example.testio.injection;

import android.support.annotation.NonNull;
import com.example.testio.interactor.LoginInteractor;
import com.example.testio.interactor.impl.LoginInteractorImpl;
import com.example.testio.presenter.LoginPresenter;
import com.example.testio.presenter.impl.LoginPresenterImpl;
import com.example.testio.presenter.loader.PresenterFactory;
import dagger.Module;
import dagger.Provides;

@Module
public final class LoginViewModule {
  @Provides
  public LoginInteractor provideInteractor() {
    return new LoginInteractorImpl();
  }

  @Provides
  public PresenterFactory<LoginPresenter> providePresenterFactory(
      @NonNull final LoginInteractor interactor) {
    return new PresenterFactory<LoginPresenter>() {
      @NonNull
      @Override
      public LoginPresenter create() {
        return new LoginPresenterImpl(interactor);
      }
    };
  }
}
