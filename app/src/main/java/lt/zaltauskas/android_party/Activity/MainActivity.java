package lt.zaltauskas.android_party.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import lt.zaltauskas.android_party.Model.Server;
import lt.zaltauskas.android_party.Model.Token;
import lt.zaltauskas.android_party.R;
import lt.zaltauskas.android_party.Request.AuthRequest;
import lt.zaltauskas.android_party.Request.ServersRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {
    private final static String BASE_URL = "http://playground.tesonet.lt/";
    public static final int STATUS_OK = 200;
    public static final int LOADING_DELAY = 800;
    private RelativeLayout loaderLayout;
    private RelativeLayout loginLayout;
    private EditText passwordField;
    private EditText usernameField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameField = findViewById(R.id.usernameInout);
        passwordField = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.buttonLogin);
        loginLayout = findViewById(R.id.loginLayout);
        loaderLayout = findViewById(R.id.preloaderLayout);
        loginButton.setOnClickListener(view -> {
            showLoader(true);
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();
            run(username, password);
        });
    }

    private void run(String username, String password) {
        AuthRequest client = getRetrofitClient().create(AuthRequest.class);
        Call<Token> tokenCall = client.requestTokenForm(
                username,
                password
        );
        requestToken(tokenCall);
    }

    private void requestToken(Call<Token> tokenCall) {
        tokenCall.enqueue(new Callback<Token>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.code() == STATUS_OK && response.body() != null) {
                    requestServerList(response.body());
                } else {
                    showLoader(false);
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                showLoader(false);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoader(boolean show) {
        if (show) {
            loaderLayout.setVisibility(View.VISIBLE);
            loginLayout.setVisibility(View.GONE);
        } else {
            loaderLayout.setVisibility(View.GONE);
            loginLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        usernameField.setText("");
        passwordField.setText("");

    }

    private void requestServerList(@NonNull Token response) {
        ServersRequest client = getRetrofitClient().create(ServersRequest.class);
        client.getServersList(response.getAccessToken()).enqueue(new Callback<List<Server>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Server>> call, Response<List<Server>> response) {
                new Handler().postDelayed(() -> {
                    showLoader(false);
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra(ListActivity.KEY_SERVER_LIST, (Serializable) response.body());
                    startActivity(intent);
                }, LOADING_DELAY);
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Server>> call, Throwable t) {
                showLoader(false);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Retrofit getRetrofitClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
