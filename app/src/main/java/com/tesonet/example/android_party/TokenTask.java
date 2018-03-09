package com.tesonet.example.android_party;

import android.os.AsyncTask;
import android.util.Log;

import com.tesonet.example.android_party.communication.TokenHandler;
import com.tesonet.example.android_party.model.TokenRequest;
import com.tesonet.example.android_party.model.TokenResponse;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by Vilius on 2018-03-08.
 */

public class TokenTask extends AsyncTask<String, Void, TokenResponse> {
    private WeakReference<MainActivity> weakActivity;
    private String name;
    private String psw;

    public TokenTask(MainActivity activity, String uName, String uPsw) {
        this.weakActivity = new WeakReference<>(activity);
        this.name = uName;
        this.psw = uPsw;
    }

    @Override
    protected TokenResponse doInBackground(String... urls) {
        TokenResponse tokenResponse = new TokenResponse();
        TokenRequest request = new TokenRequest();
        request.setUsername(name);
        request.setPassword(psw);

        try {
            TokenHandler handler = new TokenHandler(weakActivity.get());
            tokenResponse = handler.getToken(request);
        } catch (IOException e) {
            Log.e("TokT", "Error getting token plan", e);
        }
        return tokenResponse;
    }

    @Override
    protected void onPostExecute(TokenResponse response) {
        super.onPostExecute(response);
        weakActivity.get().handleToken(response);
    }
}
