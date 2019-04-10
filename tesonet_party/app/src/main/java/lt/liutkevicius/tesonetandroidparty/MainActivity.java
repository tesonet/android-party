package lt.liutkevicius.tesonetandroidparty;

import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import lt.liutkevicius.tesonetandroidparty.login.LoginController;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.controller_container)
    ViewGroup container;

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new LoginController()));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }
}
