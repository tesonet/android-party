package com.axborn.androidparty.features.rest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.axborn.androidparty.R;
import com.axborn.androidparty.features.feed.FeedActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestManager {

    private static final String TAG = RestManager.class.getName();
    private RequestQueue queue;
    private String urlGetToken = "http://playground.tesonet.lt/v1/tokens";
    private String urlGetServers = "http://playground.tesonet.lt/v1/servers";

    public RestManager(){

    }

    public void initiateRestCall(final Context context, final TextView textView) {

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", "tesonet");
            jsonBody.put("password", "partyanimal");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = jsonBody.toString();
        //TODO secure credentials

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlGetToken, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Volley Result", ""+response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String token = jsonObject.getString("token");

                    textView.append("\nActual token: " + jsonObject.get("token"));

                    retrieveServerList(context, token, textView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace(); //log the error resulting from the request for diagnosis/debugging
                //TODO no internet reaction
                //TODO bad authentication reaction
            }
        }){

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }


    private void retrieveServerList(Context context, final String token, final TextView textView) {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlGetServers, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Volley Result", ""+response);

                textView.append("\nServer list: " + response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace(); //log the error resulting from the request for diagnosis/debugging
                //TODO no internet reaction
                //TODO bad authentication reaction
            }
        }){

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + token);
                return params;
            }
        };
//make the request to your server as indicated in your request url
        Volley.newRequestQueue(context).add(stringRequest);
    }

}
