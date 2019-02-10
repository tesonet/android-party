package com.example.partyapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partyapp.Models.Server;
import com.example.partyapp.Tasks.GetServersTask;
import com.example.partyapp.Tasks.GetTokenTask;

import java.util.ArrayList;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private Boolean formHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        mProgressView.setVisibility(View.GONE);
        mLoginFormView.setVisibility(View.VISIBLE);

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (formHidden) {
            animateFormUp();
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_READ_CONTACTS) {
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                populateAutoComplete();
//            }
//        }
    }

    private void animateFormDown() {
        formHidden = true;
        mLoginFormView.animate()
                .translationY(mLoginFormView.getHeight())
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mLoginFormView.setVisibility(View.GONE);
                        mProgressView.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void animateFormUp() {
        formHidden = false;
        mLoginFormView.setVisibility(View.VISIBLE);
        mLoginFormView.animate()
                .translationY(0)
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mProgressView.setVisibility(View.GONE);
                    }
                });
    }

    private void attemptLogin() {
        animateFormDown();
        new GetTokenTask(new GetTokenTask.AsyncResponse() {

            @Override
            public void processFinish(final String token) {
                if (token == "") {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            //for some reason this only works sometimes, but in servers task works fine?..
                            //animateFormUp();
                            Toast.makeText(LoginActivity.this, "Bad login", Toast.LENGTH_SHORT).show();
                        }
                    });
//                    return;
                }

                getServerList(token);
            }
        }).execute(mEmailView.getText().toString(), mPasswordView.getText().toString());
//        }).execute("tesonet", "partyanimal");
    }

    private void getServerList(String token) {
        final Intent intent = new Intent(this, ServersActivity.class);
        new GetServersTask(token, new GetServersTask.AsyncResponse() {

            @Override
            public void processFinish(final ArrayList<Server> serverList) {
                if (serverList == null) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            animateFormUp();
                            Toast.makeText(LoginActivity.this, "No servers", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        intent.putExtra(ServersActivity.keyServers, serverList);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }, 10);


            }
        }).execute();
    }

}

