package lt.liutkevicius.tesonetandroidparty.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import lt.liutkevicius.tesonetandroidparty.R;
import lt.liutkevicius.tesonetandroidparty.base.BaseController;

public class LoginController extends BaseController {

    @BindView(R.id.username)
    AppCompatEditText username;

    @BindView(R.id.password)
    AppCompatEditText password;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.login_screen, container, false);
    }
}
