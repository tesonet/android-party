package lt.bulevicius.tessonetapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import javax.inject.Inject;

import lt.bulevicius.tessonetapp.R;
import lt.bulevicius.tessonetapp.app.TessonetApplication;
import lt.bulevicius.tessonetapp.ui.login.LoginViewImpl;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private Router router;

    @Inject
    Timber.Tree tree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TessonetApplication.getComponent().inject(this);
        Timber.plant(tree);
        setContentView(R.layout.activity_main);

        ViewGroup container = findViewById(R.id.main_frame);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new LoginViewImpl()));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }
}
