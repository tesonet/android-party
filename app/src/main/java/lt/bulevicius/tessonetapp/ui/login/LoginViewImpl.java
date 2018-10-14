package lt.bulevicius.tessonetapp.ui.login;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import lt.bulevicius.tessonetapp.R;
import lt.bulevicius.tessonetapp.app.TessonetApplication;
import lt.bulevicius.tessonetapp.ui.BaseView;
import lt.bulevicius.tessonetapp.ui.countries.CountryViewImpl;
import lt.bulevicius.tessonetapp.ui.error.GenericException;
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
     * The Login button.
     */
    @BindView(R.id.loginButton)
    AppCompatButton loginButton;

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
        getRouter().setRoot(RouterTransaction.with(new CountryViewImpl())
                                             .popChangeHandler(new FadeChangeHandler())
                                             .pushChangeHandler(new FadeChangeHandler()));
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
    @SuppressWarnings("ConstantConditions")
    public void onClick() {
        String name = userName.getText().toString();
        String pass = password.getText().toString();
        if (name == null || pass == null) {
            onError(new GenericException(getActivity().getString(R.string.please_fill_all_fields)));
            return;
        }
        if (name.length() == 0 || pass.length() == 0) {
            onError(new GenericException(getActivity().getString(R.string.please_fill_all_fields)));
            return;
        }
        presenter.doLogin(name, pass);
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
        subscriptions.add(RxView.clicks(loginButton)
                                .throttleFirst(1, TimeUnit.SECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(o -> onClick()));
    }
}
