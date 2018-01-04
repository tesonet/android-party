package com.azzdorfrobotics.android.testio.features.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.azzdorfrobotics.android.testio.BuildConfig;
import com.azzdorfrobotics.android.testio.R;
import com.azzdorfrobotics.android.testio.di.ServerComponent;
import com.azzdorfrobotics.android.testio.features.shared.BaseFragment;
import com.azzdorfrobotics.android.testio.features.shared.MainNavigator;
import com.azzdorfrobotics.android.testio.network.model.request.LoginRequest;
import javax.inject.Inject;

import static butterknife.OnTextChanged.Callback.TEXT_CHANGED;

/**
 * Created on 25.12.2017.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class LoginFragment extends BaseFragment {

    @BindView(R.id.et_username) public EditText etUsername;
    @BindView(R.id.et_password) public EditText etPassword;
    @BindView(R.id.btn_login) public Button btnLogin;

    @Inject AuthCase authCase;

    private MainNavigator mainNavigator;

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainNavigator = (MainNavigator) getActivity();
        if (BuildConfig.DEBUG) {
            etUsername.setText("tesonet");
            etPassword.setText("partyanimal");
        }
    }

    @Override protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override protected void initializeInjector(Context context) {
        getComponent(ServerComponent.class, context).inject(LoginFragment.this);
    }

    @OnClick(R.id.btn_login) void onLogin() {
        mainNavigator.showLoading(etUsername.getText().toString(), etPassword.getText().toString());
    }

    @OnTextChanged(value = { R.id.et_username, R.id.et_password }, callback = TEXT_CHANGED)
    public void validateInput(CharSequence text) {
        String name = etUsername.getText().toString();
        String pass = etPassword.getText().toString();
        if (name.length() > 0 && pass.length() > 0) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }
}
