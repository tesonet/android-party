package lt.zilinskas.marius.testio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import lt.zilinskas.marius.testio.TestioApplication;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initCustomToolbar(Toolbar toolbar) {
        if (toolbar == null) {
            return;
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public TestioApplication getTestioApplication() {
        return (TestioApplication) super.getApplication();
    }

    protected void logOut() {
        getTestioApplication().getSharedPreferences().setToken(null);
        getTestioApplication().getDatabaseHelper().getExpensesDAO().deleteAllServers();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
