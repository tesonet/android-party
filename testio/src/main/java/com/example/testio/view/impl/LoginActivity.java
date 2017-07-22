package com.example.testio.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.example.testio.R;
import com.example.testio.injection.AppComponent;
import com.example.testio.injection.DaggerLoginViewComponent;
import com.example.testio.injection.LoginViewModule;
import com.example.testio.presenter.LoginPresenter;
import com.example.testio.presenter.loader.PresenterFactory;
import com.example.testio.view.LoginView;
import javax.inject.Inject;

public final class LoginActivity extends BaseActivity<LoginPresenter, LoginView>
    implements LoginView {
  @Inject
  PresenterFactory<LoginPresenter> mPresenterFactory;

  // Your presenter is available using the mPresenter variable

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login2);

    // Your code here
    // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
  }

  @Override
  protected void setupComponent(@NonNull AppComponent parentComponent) {
    DaggerLoginViewComponent.builder()
        .appComponent(parentComponent)
        .loginViewModule(new LoginViewModule())
        .build()
        .inject(this);
  }

  @NonNull
  @Override
  protected PresenterFactory<LoginPresenter> getPresenterFactory() {
    return mPresenterFactory;
  }
}
