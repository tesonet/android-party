package lt.liutkevicius.tesonetandroidparty.ui.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import lt.liutkevicius.tesonetandroidparty.PartyApp;
import lt.liutkevicius.tesonetandroidparty.R;
import lt.liutkevicius.tesonetandroidparty.ui.base.BaseView;
import lt.liutkevicius.tesonetandroidparty.ui.progress.ProgressViewImpl;
import lt.liutkevicius.tesonetandroidparty.ui.servers.ServersViewImpl;

import javax.inject.Inject;

public class LoginViewImpl extends BaseView implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.username)
    AppCompatEditText username;

    @BindView(R.id.password)
    AppCompatEditText password;

    private ProgressViewImpl progressView;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.screen_login, container, false);
    }

    @Override
    public void doInjection() {
        PartyApp.getAppComponent().inject(this);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        loginPresenter.setView(this);
    }

    @OnClick(R.id.bt_login)
    void onLoginClicked() {
        String usernameStr = username.getText().toString().toLowerCase().trim();
        String passStr = password.getText().toString().toLowerCase().trim();
        if (usernameStr.isEmpty() || passStr.isEmpty()) {
            onError(new Exception(getActivity().getString(R.string.input_validation_error)));
        } else {
            loginPresenter.login(usernameStr, passStr);
        }
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoggedIn() {
        progressView.setLoadingText(getActivity().getString(R.string.fetching_list));
    }

    @Override
    public void showLoading() {
        progressView = new ProgressViewImpl(getActivity().getString(R.string.logging_in));

        getRouter().pushController(RouterTransaction.with(progressView)
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    public void hideLoading() {
        getRouter().popCurrentController();
    }


    @Override
    public void showServers() {
        getRouter().setRoot(RouterTransaction.with(new ServersViewImpl())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }
}
