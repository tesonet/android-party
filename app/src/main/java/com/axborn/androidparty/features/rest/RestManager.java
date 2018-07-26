package com.axborn.androidparty.features.rest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestManager {

    private static final String TAG = RestManager.class.getName();
    private RequestQueue queue;
    private String urlGetToken = "http://playground.tesonet.lt/v1/tokens";
    private String urlGetServers = "http://playground.tesonet.lt/v1/servers";

    public RestManager(){

    }

    public void initiateRestCall(final Context context, final ListView listView) {

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

                    retrieveServerList(context, token, listView);

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


    private void retrieveServerList(final Context context, final String token, final ListView listView) {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlGetServers, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Volley Result", ""+response);

                ArrayList<HashMap<String, String>> serverList = new ArrayList<>();

                try {
                    JSONArray jsonObj = new JSONArray(response);

                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject c = jsonObj.getJSONObject(i);

                        String name = c.getString("name");
                        String distance = c.getString("distance");

                        // tmp hash map for single contact
                        HashMap<String, String> server = new HashMap<>();

                        // adding each child node to HashMap key => value
                        server.put("name", name);
                        server.put("distance", distance);

                        // adding contact to contact list
                        serverList.add(server);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ListAdapter adapter = new SimpleAdapter(
                        context, serverList,
                        R.layout.list_item, new String[]{"name", "distance"},
                        new int[]{R.id.name, R.id.distance});

                listView.setAdapter(adapter);


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
