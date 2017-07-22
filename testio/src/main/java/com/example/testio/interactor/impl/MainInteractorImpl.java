package com.example.testio.interactor.impl;

import com.example.testio.api.TestioApi;
import com.example.testio.interactor.MainInteractor;
import javax.inject.Inject;

public final class MainInteractorImpl implements MainInteractor {

  private TestioApi testioApi;

  @Inject
  public MainInteractorImpl(TestioApi testioApi) {
    this.testioApi = testioApi;
  }
}