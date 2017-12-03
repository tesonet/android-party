package com.tesonet.justas.androidparty;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceManager;

import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends BaseActivity{

    private HttpClient mClient;
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;

    private View mLoginFormView;

    private ResponseListener mLoginListener;
    private ResponseListener mServerListener;

    @Override
    protected void init() {
        super.init();
        initializeViews();
        initializeCallbacks();
        mClient = new HttpClient(Volley.newRequestQueue(this));
        attemptDisplayServers();
    }

    private void initializeViews() {
        setContentView(R.layout.activity_login);
        mUsernameView = findViewById(R.id.username);
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void initializeCallbacks()
    {
        mLoginListener = new ResponseListener(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                onLoggedIn(response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                showToast(getString(R.string.login_failure));
                super.onErrorResponse(error);
                showProgress(false);
            }
        };

        mServerListener = new ResponseListener(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                onGetServers(response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                showProgress(false);
            }
        };
    }

    private void attemptDisplayServers() {
        if (PreferenceManager.getDefaultSharedPreferences(this).contains(SERVERS_PREFERENCE)){
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            showProgress(false);
        }
    }

    private void attemptLogin() {
        showProgress(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject parameters = new JSONObject();
                    parameters.put("username", mUsernameView.getText().toString());
                    parameters.put("password", mPasswordView.getText().toString());


                    mClient.request(mClient.createRequest("tokens", Request.Method.POST, mLoginListener)
                            .setParameters(parameters.toString()));
                }
                catch(JSONException exception)
                {
                    exception.printStackTrace();
                    showToast(exception.getLocalizedMessage());
                }
            }
        }, 2000);


    }

    private void getServers(String token)
    {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        mClient.request(mClient.createRequest(SERVERS_PREFERENCE, Request.Method.GET, mServerListener)
                .setHeader(headers));
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    public void onLoggedIn(String tokenJson) {
        try{
            JSONObject jsonObj = new JSONObject(tokenJson);
            getServers(jsonObj.getString("token"));
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void onGetServers(String serversJson) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("servers", serversJson).apply();
        showToast(getString(R.string.login_success));
        attemptDisplayServers();
    }
}

