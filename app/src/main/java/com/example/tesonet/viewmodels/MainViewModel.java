package com.example.tesonet.viewmodels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tesonet.database.models.Token;
import com.example.tesonet.database.models.User;
import com.example.tesonet.network.ApiInterface;
import com.example.tesonet.network.Service;
import com.example.tesonet.repositories.TokenRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    private TokenRepository tokenRepository;
    private ApiInterface apiInterface;
    private Context context;
    public String tokenString;
    public boolean loginSucceded;

    private LiveData<Token> token;

    public MainViewModel(@NonNull Application application) {
        super(application);
        apiInterface = Service.getRetrofitInstance().create(ApiInterface.class);
        context = application.getApplicationContext();
        tokenRepository = new TokenRepository(application);
        token = tokenRepository.getTokenValue();
        loginSucceded = false;
    }

    public void sendLoginRequest(String username, String password) {
        final Call<Token> tokenResponse = apiInterface.login(new User(username, password));
        tokenResponse.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    tokenRepository.insert(response.body());
                    tokenString = response.body().getToken();
                    loginSucceded = true;
                } else {
                    tokenString = "";
                    Toast.makeText(context, "Login not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isLoginSucceded() {
        return loginSucceded;
    }

    public void deleteToken() {
        tokenRepository.deleteToken();
    }

    public LiveData<Token> getToken() {
        return token;
    }
}
