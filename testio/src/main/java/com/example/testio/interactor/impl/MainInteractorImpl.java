package com.example.testio.interactor.impl;

import com.example.testio.api.TestioApi;
import com.example.testio.helpers.TokenStorage;
import com.example.testio.interactor.MainInteractor;
import com.example.testio.models.Server;
import com.example.testio.models.Token;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import java.util.List;
import javax.inject.Inject;

public final class MainInteractorImpl implements MainInteractor {

  private TokenStorage tokenStorage;
  private TestioApi testioApi;

  @Inject
  public MainInteractorImpl(TestioApi testioApi, TokenStorage tokenStorage) {
    this.testioApi = testioApi;
    this.tokenStorage = tokenStorage;
  }

  @Override
  public Single<List<Server>> getServerList(String token) {
    return testioApi.getServers("Bearer " + token);
  }

  @Override
  public Single<Token> getToken() {
    return Single.create(new SingleOnSubscribe<Token>() {
      @Override
      public void subscribe(@NonNull SingleEmitter<Token> e) throws Exception {
        String string = tokenStorage.getToken();
        if (string != null) {
          Token token = new Token();
          token.setToken(string);
          e.onSuccess(token);
        } else {
          e.onError(new Throwable("No token"));
        }
      }
    });
  }

  @Override
  public Completable releaseToken() {
    return Completable.create(new CompletableOnSubscribe() {
      @Override
      public void subscribe(@NonNull CompletableEmitter e) throws Exception {
        tokenStorage.storeToken(null);
        e.onComplete();
      }
    });
  }
}