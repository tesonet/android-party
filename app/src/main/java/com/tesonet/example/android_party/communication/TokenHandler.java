package com.tesonet.example.android_party.communication;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tesonet.example.android_party.model.TokenRequest;
import com.tesonet.example.android_party.model.TokenResponse;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Vilius on 2018-03-08.
 */

public class TokenHandler extends BaseOkHttpClient {
    private static final String METHOD = "tokens";

    public TokenHandler(Context context) {
        super(context);
        setEndpoint(METHOD);
    }

    public TokenResponse getToken(TokenRequest tr) throws IOException {
        Request request = buildPostRequest(tr);
        TokenResponse tokenResponse;
        String json = null;
        try {
            Response response = client.newCall(request).execute();
            String status = response.message() + "(" + response.code() + ")";
            if (response.isSuccessful()) {
                json = response.body().string();
                tokenResponse = new Gson().fromJson(json, TokenResponse.class);
            } else {
                throw new IOException("Token query failed with status: " + status);
            }
        } catch (IOException e) {
            throw new IOException("Token request failed", e);
        } catch (JsonSyntaxException e) {
            throw new IOException("Failed to parse JSON: " + json, e);
        }
        return tokenResponse;
    }
}
