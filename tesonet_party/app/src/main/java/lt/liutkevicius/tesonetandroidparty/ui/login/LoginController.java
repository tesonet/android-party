package lt.liutkevicius.tesonetandroidparty.ui.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.bluelinelabs.conductor.RouterTransaction;
import lt.liutkevicius.tesonetandroidparty.R;
import lt.liutkevicius.tesonetandroidparty.ui.base.BaseController;
import lt.liutkevicius.tesonetandroidparty.ui.progress.ProgressController;

public class LoginController extends BaseController {

    @BindView(R.id.username)
    AppCompatEditText username;

    @BindView(R.id.password)
    AppCompatEditText password;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.screen_login, container, false);
    }

    @OnClick(R.id.bt_login)
    public void onLoginClicked() {
        getRouter().pushController(RouterTransaction.with(new ProgressController()));
    }
}
