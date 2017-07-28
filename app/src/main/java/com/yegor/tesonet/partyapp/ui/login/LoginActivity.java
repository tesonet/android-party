package com.yegor.tesonet.partyapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.yegor.tesonet.partyapp.ui.list.ListActivity;
import com.yegor.tesonet.partyapp.R;
import com.yegor.tesonet.partyapp.events.LoginSuccessfulEvent;
import com.yegor.tesonet.partyapp.framework.ApplicationSingleton;
import com.yegor.tesonet.partyapp.framework.BaseActivity;
import com.yegor.tesonet.partyapp.model.Account;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends BaseActivity implements RegexpInputView.TextStatusChangedListener {

    @BindView(R.id.username)
    RegexpInputView username;
    @BindView(R.id.password)
    RegexpInputView password;
    @BindView(R.id.submit)
    Button submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUnBinder = ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        username.setStatusListener(this);
        password.setStatusListener(this);
    }

    @Override
    public void onNewTextStatus(boolean status) {
        if (username.getStatus() && password.getStatus()) {
            submit.setActivated(true);
        } else {
            submit.setActivated(false);
        }
    }

    @Subscribe
    @SuppressWarnings("unused")
    void onLoginSuccess(LoginSuccessfulEvent event) {
        hideWaitDialog();
        startActivity(new Intent(this, ListActivity.class));
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @OnClick(R.id.submit)
    @SuppressWarnings("unused")
    void login() {
        if (submit.isActivated()) {
            showWaitDialog("Logging in...");
            Account account = new Account();
            account.setUsername(username.getText());
            account.setPassword(password.getText());
            ApplicationSingleton.getInstance().getApi().login(account);
        }
    }
}

