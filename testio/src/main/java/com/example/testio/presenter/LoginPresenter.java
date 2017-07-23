package com.example.testio.presenter;

import com.example.testio.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView> {

  void tryLogin(String userName, String password);
}