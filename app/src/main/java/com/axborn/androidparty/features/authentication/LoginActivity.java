package com.axborn.androidparty.features.authentication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.axborn.androidparty.R;
import com.axborn.androidparty.features.loading.LoadingActivity;
import com.axborn.androidparty.features.rest.RestAPI;
import com.axborn.androidparty.structure.TokensResponse;
import com.axborn.androidparty.structure.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{

    private EditText usernameView;
    private EditText passwordView;
    private View progressView;
    private View loginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        usernameView = findViewById(R.id.username_field);
        passwordView = findViewById(R.id.password_field);
        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);

        Button logInButton = findViewById(R.id.log_in_button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress(true);

                usernameView.setError(null);
                passwordView.setError(null);

                String username = usernameView.getText().toString();
                String password = passwordView.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    usernameView.setError(getString(R.string.error_field_required));
                    usernameView.requestFocus();
                    showProgress(false);
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordView.setError(getString(R.string.error_field_required));
                    passwordView.requestFocus();
                    showProgress(false);
                    return;
                }

                final User user = new User(username, password);

                RestAPI gitHubService = RestAPI.retrofit.create(RestAPI.class);
                final Call<TokensResponse> call =
                        gitHubService.retrieveToken(user);

                call.enqueue(new Callback<TokensResponse>() {
                    @Override
                    public void onResponse(Call<TokensResponse> call, Response<TokensResponse> response) {
                        if(response.isSuccessful()) {
                            TokensResponse tokensResponse = response.body();
                            Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);
                            intent.putExtra("TOKEN", tokensResponse.getToken());
                            //TODO pass serializable
                            LoginActivity.this.startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        } else {
                            showProgress(false);
                            passwordView.setError(getString(R.string.error_invalid_credentials));
                            passwordView.requestFocus();
                        }
                    }
                    @Override
                    public void onFailure(Call<TokensResponse> call, Throwable t) {
                        showProgress(false);
                        passwordView.setError(getString(R.string.error_invalid_credentials));
                        passwordView.requestFocus();
                    }
                });
            }
        });

    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });

    }


}