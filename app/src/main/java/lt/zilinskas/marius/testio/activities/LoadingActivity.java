package lt.zilinskas.marius.testio.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import lt.zilinskas.marius.testio.R;
import lt.zilinskas.marius.testio.fragments.LoadingFragment;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        if (savedInstanceState == null) {
            initFragment();
        }
    }

    private void initFragment() {
        Fragment fragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, fragment.getClass().getName())
                .commit();
    }
}
