package com.tesonet.example.android_party;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Vilius on 2018-03-07.
 */

public class LoginFragment extends Fragment {
    private EditText userName;
    private EditText userPsw;
    private Button submit;
    private ConstraintLayout constLayout;
    private MainActivity activity;

    public LoginFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        userName = (EditText) v.findViewById(R.id.userTxt);
        userPsw = (EditText) v.findViewById(R.id.pswTxt);
        submit = (Button) v.findViewById(R.id.submitBtn);
        constLayout = (ConstraintLayout) v.findViewById(R.id.constraint);

        userName.setCursorVisible(false);
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() > 0) {
                    userName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    userName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_username, 0, 0, 0);
                }
            }
        });

        userPsw.setCursorVisible(false);
        userPsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() > 0) {
                    userPsw.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    userPsw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, 0, 0);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    if (userName.getText().toString().isEmpty() || userPsw.getText().toString().isEmpty()) {
                        Snackbar snackbar = Snackbar.make(constLayout, "No username or password submitted", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        activity.showLoadingFrag();
                        getToken();
                    }
                } else {
                    Snackbar snackbar = Snackbar.make(constLayout, "No internet connection", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        return v;
    }

    private void getToken() {
        TokenTask task = new TokenTask(activity, userName.getText().toString(), userPsw.getText().toString());
        task.execute();
        activity.showLoadingFrag();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } else {
            return false;
        }
    }
}
