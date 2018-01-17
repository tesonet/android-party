package lajevski.radoslav.tesonetparty.ui.fragments.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lajevski.radoslav.tesonetparty.R;
import lajevski.radoslav.tesonetparty.di.ActivityComponent;
import lajevski.radoslav.tesonetparty.ui.activities.MainActivity;
import lajevski.radoslav.tesonetparty.ui.base.BaseFragment;

/**
 * Created by Radoslav on 1/16/2018.
 */

public class LoginFragment extends BaseFragment implements LoginMvpView {


    @OnClick(R.id.f_login_b_submit)
    public void login(View view) {

        mPresenter.onLoginButtonClick(mETEmail.getEditText().getText().toString(), mETPassword.getEditText().getText().toString());
    }


    @BindView(R.id.f_login_b_submit)
    Button mBtnLogin;

    @BindView(R.id.f_login_et_username)
    TextInputLayout mETEmail;

    @BindView(R.id.f_login_et_password)
    TextInputLayout mETPassword;

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    public LoginFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f_login, container, false);

        ButterKnife.bind(this, view);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
            mPresenter.onViewPrepared();
        }

        if (getToolbar() != null) {
            getToolbar().setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void setLoginButtonStatus(boolean status) {
        mBtnLogin.setEnabled(status);
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(getActivity());
        startActivity(intent);
        getActivity().finish();
    }
}

