package com.example.testio.view;

import android.support.annotation.UiThread;

@UiThread
public interface LoginView {

  void failure();

  void loginSuccessful();
}