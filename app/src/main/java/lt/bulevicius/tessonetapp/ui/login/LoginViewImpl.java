package lt.bulevicius.tessonetapp.ui.login;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lt.bulevicius.tessonetapp.R;
import lt.bulevicius.tessonetapp.app.TessonetApplication;
import lt.bulevicius.tessonetapp.ui.BaseView;
import lt.bulevicius.tessonetapp.ui.countries.CountryViewImpl;
import lt.bulevicius.tessonetapp.ui.progress.ProgressView;
import timber.log.Timber;

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
    private ProgressView controller;

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        presenter.setView(this);
    }

    @Override
    protected void onDetach(@NonNull View view) {
        Timber.d("onDetach");
        super.onDetach(view);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        Timber.d("onDestroyView");
        presenter.setView(null);
        super.onDestroyView(view);
    }

    @Override
    @SuppressWarnings("all")
    public void loginSuccess() {
        controller.setNewProgressTitle(getActivity().getString(R.string.fetching_data));
    }

    @Override
    public void onDataSuccess() {
        getRouter().setRoot(RouterTransaction.with(new CountryViewImpl()));
    }

    @Override
    @SuppressWarnings("all")
    public void onError(Throwable error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    @SuppressWarnings("all")
    public void showProgress() {
        controller = new ProgressView(getActivity().getString(R.string.logging_in));
        getRouter().pushController(RouterTransaction.with(controller)
                                                    .popChangeHandler(new FadeChangeHandler())
                                                    .pushChangeHandler(new FadeChangeHandler()));
    }

    @Override
    public void hideProgress() {
        getRouter().popController(controller);
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
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }
}
