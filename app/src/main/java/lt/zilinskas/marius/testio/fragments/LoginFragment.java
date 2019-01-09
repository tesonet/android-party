package lt.zilinskas.marius.testio.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;
import lt.zilinskas.marius.testio.R;
import lt.zilinskas.marius.testio.api.entities.Token;
import lt.zilinskas.marius.testio.api.entities.UserInfo;
import lt.zilinskas.marius.testio.utils.StringUtils;

public class LoginFragment extends BaseFragment {

    private static final String SAVED_USERNAME = "username";
    private static final String SAVED_PASSWORD = "password";

    private CustomViewHolder customViewHolder;
    private CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        compositeDisposable = new CompositeDisposable();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        customViewHolder = new CustomViewHolder(view, savedInstanceState);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SAVED_USERNAME, customViewHolder.username.getText().toString());
        outState.putString(SAVED_PASSWORD, customViewHolder.username.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    private void login() {
        String username = customViewHolder.username.getText().toString();
        String password = customViewHolder.password.getText().toString();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            Toast.makeText(getContext(), getString(R.string.enter_username_and_password), Toast.LENGTH_LONG).show();
            return;
        }

        Disposable disposable = testioApplication.getPlaygroundApi()
                .authorize(new UserInfo(username, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());
        compositeDisposable.add(disposable);
    }

    private DisposableObserver<Token> getObserver() {
        return new DisposableObserver<Token>() {
            @Override
            public void onNext(Token token) {
                testioApplication.getSharedPreferences().setToken(token);
                startLoadingFragment();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof HttpException && ((HttpException) e).code() == 401) {
                    Toast.makeText(getContext(), getString(R.string.incorrect_username_or_password), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), getString(R.string.unable_to_login), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onComplete() {
            }
        };
    }

    private void startLoadingFragment() {
        if (getActivity() == null) {
            return;
        }

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LoadingFragment(), LoadingFragment.class.getName())
                .commit();
    }


    @Getter
    @Setter
    private class CustomViewHolder {
        private View parent;
        private EditText username;
        private EditText password;
        private Button login;

        CustomViewHolder(@NonNull View parent, @Nullable Bundle savedInstanceSate) {
            this.parent = parent;
            initViews();
            initListeners();
            restoreState(savedInstanceSate);
        }

        private void initViews() {
            username = parent.findViewById(R.id.usernameEditText);
            password = parent.findViewById(R.id.passwordEditText);
            login = parent.findViewById(R.id.loginButton);
        }

        private void initListeners() {
            login.setOnClickListener(v -> login());
        }

        private void restoreState(Bundle savedInstanceState) {
            if (savedInstanceState == null) {
                return;
            }

            username.setText(savedInstanceState.getString(SAVED_USERNAME, ""));
            password.setText(savedInstanceState.getString(SAVED_PASSWORD, ""));
        }
    }
}
