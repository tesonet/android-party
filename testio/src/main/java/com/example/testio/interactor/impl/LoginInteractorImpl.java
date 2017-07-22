package com.example.testio.interactor.impl;

import com.example.testio.api.TestioApi;
import com.example.testio.interactor.LoginInteractor;
import com.example.testio.models.Server;
import com.example.testio.models.Token;
import com.example.testio.models.User;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public final class LoginInteractorImpl implements LoginInteractor {

  private TestioApi testioApi;

  @Inject
  public LoginInteractorImpl(TestioApi testioApi) {
    this.testioApi = testioApi;
  }

  @Override
  public Observable<Token> getToken(User user) {
    return testioApi.postUserGetToken("application/json; charset=utf-8", user);
  }

  @Override
  public Observable<List<Server>> getServersList(String token) {
    return testioApi.getServers("Bearer "+token);
  }
}