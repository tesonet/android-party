package com.example.testio.injection;

import android.support.annotation.NonNull;
import com.example.testio.api.TestioApi;
import com.example.testio.helpers.TokenStorage;
import com.example.testio.interactor.MainInteractor;
import com.example.testio.interactor.impl.MainInteractorImpl;
import com.example.testio.presenter.MainPresenter;
import com.example.testio.presenter.impl.MainPresenterImpl;
import com.example.testio.presenter.loader.PresenterFactory;
import dagger.Module;
import dagger.Provides;

@Module
public final class MainViewModule {
  @Provides
  public MainInteractor provideInteractor(TestioApi testioApi, TokenStorage tokenStorage) {
    return new MainInteractorImpl(testioApi, tokenStorage);
  }

  @Provides
  public PresenterFactory<MainPresenter> providePresenterFactory(
      @NonNull final MainInteractor interactor) {
    return new PresenterFactory<MainPresenter>() {
      @NonNull
      @Override
      public MainPresenter create() {
        return new MainPresenterImpl(interactor);
      }
    };
  }
}
