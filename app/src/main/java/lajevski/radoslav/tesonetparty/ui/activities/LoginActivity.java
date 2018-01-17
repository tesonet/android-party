package lajevski.radoslav.tesonetparty.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import lajevski.radoslav.tesonetparty.R;
import lajevski.radoslav.tesonetparty.ui.base.BaseActivity;
import lajevski.radoslav.tesonetparty.ui.fragments.login.LoginFragment;

public class LoginActivity extends BaseActivity {

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected Fragment getFragment() {
        return LoginFragment.newInstance();
    }

    @Override
    protected String getFragmentTag() {
        return LoginFragment.class.getSimpleName();
    }

    @Override
    protected Integer getContentView() {
        return R.layout.a_main;
    }
}
