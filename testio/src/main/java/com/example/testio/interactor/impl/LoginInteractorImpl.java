package com.example.testio.interactor.impl;

import com.example.testio.api.TestioApi;
import com.example.testio.helpers.TokenStorage;
import com.example.testio.interactor.LoginInteractor;
import com.example.testio.models.Token;
import com.example.testio.models.User;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.CompletableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import javax.inject.Inject;

public final class LoginInteractorImpl implements LoginInteractor {

  private TokenStorage tokenStorage;
  private TestioApi testioApi;

  @Inject
  public LoginInteractorImpl(TestioApi testioApi, TokenStorage tokenStorage) {
    this.testioApi = testioApi;
    this.tokenStorage = tokenStorage;
  }

  @Override
  public Completable tryLogin(User user) {
    return testioApi.postUserGetToken("application/json; charset=utf-8", user)
        .flatMapCompletable(new Function<Token, CompletableSource>() {
          @Override
          public CompletableSource apply(@NonNull Token token) throws Exception {
            try {
              tokenStorage.storeToken(token.getToken());
              return Completable.complete();
            } catch (Exception e) {
              e.printStackTrace();
              return Completable.error(e);
            }
          }
        });
  }

  @Override
  public Completable checkToken() {
    return Completable.create(new CompletableOnSubscribe() {
      @Override
      public void subscribe(@NonNull CompletableEmitter e) throws Exception {
        String token = tokenStorage.getToken();
        if (token == null) {
          e.onError(new Throwable("No token"));
        } else {
          e.onComplete();
        }
      }
    });
  }
}