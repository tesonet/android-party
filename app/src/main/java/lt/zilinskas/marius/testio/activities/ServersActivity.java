package lt.zilinskas.marius.testio.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageButton;

import lt.zilinskas.marius.testio.R;
import lt.zilinskas.marius.testio.fragments.ServersFragment;

public class ServersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servers);
        initCustomToolbar(findViewById(R.id.toolbar));
        initListeners();

        if (savedInstanceState == null) {
            initFragment();
        }
    }

    private void initListeners() {
        ImageButton logOutButton = findViewById(R.id.log_out_button);
        logOutButton.setOnClickListener(v -> logOut());
    }

    private void initFragment() {
        Fragment fragment = new ServersFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, fragment.getClass().getName())
                .commit();
    }
}
