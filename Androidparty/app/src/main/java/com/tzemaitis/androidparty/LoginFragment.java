package com.tzemaitis.androidparty;

import android.content.Context;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private LoginFragmentListener mListener;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        root.findViewById(R.id.buttonLogin).setOnClickListener(this);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragmentListener) {
            mListener = (LoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        TextView usernameEdit = (TextView)getView().findViewById(R.id.editUsername);
        String username = usernameEdit.getText().toString();
        if (username.isEmpty()) {
            // TODO show error message in dialog or TextView in LoginFragment layout, mark edit text
            // in red color to notify user about problem in input field

            Toast.makeText(getActivity(), "User name not entered", Toast.LENGTH_SHORT).show();

            return;
        }

        TextView passwordEdit = (TextView)getView().findViewById(R.id.editPassword);
        String password = passwordEdit.getText().toString();
        if (password.isEmpty()) {
            // TODO show error message in dialog or TextView in LoginFragment layout, mark edit text
            // in red color to notify user about problem in input field

            Toast.makeText(getActivity(), "Password not entered", Toast.LENGTH_SHORT).show();

            return;
        }

        UserAccount account = new UserAccount(username, password);
        PartyApplication app = (PartyApplication) getActivity().getApplication();
        Call<AuthenticationToken> call = app.getRestApi().login(account);

        call.enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                AuthenticationToken token = response.body();
                if (token != null && !token.getToken().isEmpty()) {
                    mListener.onLogin(response.body());
                } else {
                    // TODO notify GUI about invalid credentials
                    Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                call.cancel();
                // TODO update GUI with information about failure
                Toast.makeText(getActivity(), "Failed to authenticate", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface LoginFragmentListener {
        void onLogin(AuthenticationToken token);
    }
}
