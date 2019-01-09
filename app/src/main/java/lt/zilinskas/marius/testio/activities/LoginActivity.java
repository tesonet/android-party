package lt.zilinskas.marius.testio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import lt.zilinskas.marius.testio.R;
import lt.zilinskas.marius.testio.fragments.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if user has already logged in and downloaded server data
        jumpToOtherActivity();

        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            initFragment();
        }
    }

    private void jumpToOtherActivity() {
        // Jump to Servers activity if servers have already been downloaded
        if (getTestioApplication().getDatabaseHelper().getExpensesDAO().hasObjects()) {
            Intent intent = new Intent(this, ServersActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Jump to Loading activity to download servers if user has already logged in
        if (getTestioApplication().getSharedPreferences().getToken() != null) {
            Intent intent = new Intent(this, LoadingActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initFragment() {
        Fragment fragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, fragment.getClass().getName())
                .commit();
    }
}
