package com.example.testio.view.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
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

  @BindView(R.id.activity_loginRoot)
  View root;

  @BindView(R.id.linearLayout)
  View linearLayout;

  @BindView(R.id.progressBar)
  ProgressBar progressBar;

  @BindView(R.id.textView_loading)
  TextView textView_loading;

  @BindView(R.id.editText_UserName)
  EditText userName;

  @BindView(R.id.editText_Password)
  EditText passWord;

  @BindView(R.id.button_login)
  Button buttonLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    ButterKnife.bind(this);

    buttonLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        hideKeyboard();

        if (isValid(userName.getText().toString(), passWord.getText().toString())) {
          isLoading(true);
          final Handler handler = new Handler();
          handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              mPresenter.tryLogin(userName.getText().toString(), passWord.getText().toString());
            }
          }, 2000);
        } else {
          showError(userName.getText().toString(), passWord.getText().toString());
        }
      }
    });

    // Your code here
    // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
  }

  private void hideKeyboard() {
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(buttonLogin.getWindowToken(), 0);
  }

  private void isLoading(boolean isLoading) {
    if (isLoading) {
      linearLayout.setVisibility(View.GONE);
      textView_loading.setVisibility(View.VISIBLE);
      progressBar.setVisibility(View.VISIBLE);
    } else {
      linearLayout.setVisibility(View.VISIBLE);
      textView_loading.setVisibility(View.GONE);
      progressBar.setVisibility(View.GONE);
    }
  }

  private void showError(String userNameText, String passwordText) {
    if (userNameText.length() == 0) {
      if (!userName.hasFocus()) userName.requestFocus();
      userName.setError("Username must not be empty");
    } else {
      if (userNameText.length() < 6) {
        if (!userName.hasFocus()) userName.requestFocus();
        userName.setError("Username too short");
      } else {
        if (passwordText.length() == 0) {
          if (!passWord.hasFocus()) passWord.requestFocus();
          passWord.setError("Password must not be empty");
        } else {
          if (passwordText.length() < 6) {
            if (!passWord.hasFocus()) passWord.requestFocus();
            passWord.setError("Password too short");
          }
        }
      }
    }
  }

  private boolean isValid(String userName, String password) {
    boolean valid = false;
    if (userName.length() >= 6 && password.length() >= 6) {
      valid = true;
    }
    return valid;
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

  @Override
  public void failure() {
    isLoading(false);
    Snackbar.make(root, "Login failed", Snackbar.LENGTH_LONG).show();
  }

  @Override
  public void loginSuccessful() {
    //isLoading(false);
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }
}
