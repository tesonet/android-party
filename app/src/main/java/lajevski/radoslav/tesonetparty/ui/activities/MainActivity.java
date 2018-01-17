package lajevski.radoslav.tesonetparty.ui.activities;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import lajevski.radoslav.tesonetparty.R;
import lajevski.radoslav.tesonetparty.ui.base.BaseActivity;
import lajevski.radoslav.tesonetparty.ui.fragments.servers.ServersFragment;

public class MainActivity extends BaseActivity {

    public static Intent getStartIntent(Context context) {
     return new Intent(context, MainActivity.class);}

    @Override
    protected Fragment getFragment() {
        return ServersFragment.newInstance();
    }

    @Override
    protected String getFragmentTag() {
        return ServersFragment.class.getSimpleName();
    }

    @Override
    protected Integer getContentView() {
        return R.layout.a_main;
    }
}
