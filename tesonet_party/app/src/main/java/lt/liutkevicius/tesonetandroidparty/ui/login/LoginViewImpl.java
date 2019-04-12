package lt.liutkevicius.tesonetandroidparty.ui.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    ProgressViewImpl progressView;

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
    public void onLoginClicked() {
        loginPresenter.login(username.getText().toString().toLowerCase().trim(),
                password.getText().toString().toLowerCase().trim());
    }

    @Override
    public void onError() {
        // TODO implement
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
    public void showServers() {
        getRouter().setRoot(RouterTransaction.with(new ServersViewImpl())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }
}
