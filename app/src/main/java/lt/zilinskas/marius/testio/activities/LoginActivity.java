package lt.zilinskas.marius.testio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import lt.zilinskas.marius.testio.R;
import lt.zilinskas.marius.testio.fragments.LoadingFragment;
import lt.zilinskas.marius.testio.fragments.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if user has already logged in and downloaded server data
        jumpToOtherScreen();

        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            initFragment(new LoginFragment());
        }
    }

    private void jumpToOtherScreen() {
        // Jump to Servers activity if servers have already been downloaded
        if (getTestioApplication().getDatabaseHelper().getExpensesDAO().hasObjects()) {
            Intent intent = new Intent(this, ServersActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Initialise loading fragment to download servers if user has already logged in
        if (getTestioApplication().getSharedPreferences().getToken() != null) {
            initFragment(new LoadingFragment());
        }
    }

    private void initFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, fragment.getClass().getName())
                .commit();
    }
}
