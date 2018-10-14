package lt.bulevicius.tessonetapp.ui.login;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lt.bulevicius.tessonetapp.R;
import lt.bulevicius.tessonetapp.app.TessonetApplication;
import lt.bulevicius.tessonetapp.ui.BaseView;
import lt.bulevicius.tessonetapp.ui.countries.CountryViewImpl;

/**
 * The type Login view.
 */
public final class LoginViewImpl extends BaseView implements LoginView {

    /**
     * The Presenter.
     */
    @Inject
    LoginPresenter presenter;

    /**
     * The User name.
     */
    @BindView(R.id.userNameEditText)
    AppCompatEditText userName;

    /**
     * The Password.
     */
    @BindView(R.id.userPasswordEditText)
    AppCompatEditText password;

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        presenter.setView(this);
    }

    @Override
    protected void onDetach(@NonNull View view) {
        presenter.setView(null);
        super.onDetach(view);
    }

    @Override
    public void loginSuccess() {
        getRouter().setRoot(RouterTransaction.with(new CountryViewImpl()));
    }

    @Override
    public void countrySuccess() {

    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    /**
     * On click.
     */
    @OnClick(R.id.loginButton)
    @SuppressWarnings("ConstantConditions")
    public void onClick() {
        presenter.doLogin(userName.getText().toString(), password.getText().toString());
    }

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.view_login, container, false);
    }

    @Override
    public void doInject() {
        TessonetApplication.getComponent().inject(this);
    }

    @Override
    public void doBindViews(View view) {
        ButterKnife.bind(this, view);
    }
}
