package com.example.testio.interactor;

import com.example.testio.models.User;
import io.reactivex.Completable;

public interface LoginInteractor extends BaseInteractor {

  Completable tryLogin(User user);

  Completable checkToken();
}