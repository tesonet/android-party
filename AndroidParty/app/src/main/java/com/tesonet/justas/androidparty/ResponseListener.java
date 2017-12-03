package com.tesonet.justas.androidparty;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ResponseListener implements Response.Listener<String>, Response.ErrorListener {
    @Override
    public void onResponse(String response){
        Log.i("Login","response: " + response);
    }

    @Override
    public void onErrorResponse(VolleyError error) { Log.e("Login", "" + error.getLocalizedMessage()); }
}
