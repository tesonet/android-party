package com.axborn.androidparty.features.rest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.axborn.androidparty.BuildConfig;
import com.axborn.androidparty.R;
import com.axborn.androidparty.features.database.DatabaseManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class RestManagerVolley {

    public RestManagerVolley(){

    }

    public void initiateRestCall(final Context context, final ListView listView) {
        try {
            //For presentation purposes
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BuildConfig.URL_GET_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                error.printStackTrace();
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
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("username", BuildConfig.TESONET_USERNAME);
                    jsonBody.put("password", BuildConfig.TESONET_PASSWORD);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //TODO secure credentials

                final String requestBody = jsonBody.toString();
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

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BuildConfig.URL_GET_SERVERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               ArrayList<HashMap<String, String>> serverList = new ArrayList<>();

                try {
                    JSONArray jsonObj = new JSONArray(response);
                    DatabaseManager databaseManager = new DatabaseManager(context);
                    databaseManager.cleanServerList();

                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject c = jsonObj.getJSONObject(i);

                        String name = c.getString("name");
                        String distance = c.getString("distance");

                        HashMap<String, String> server = new HashMap<>();

                        server.put("name", name);
                        server.put("distance", distance + " km");

                        serverList.add(server);
                        databaseManager.insertServer(name, distance);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ListAdapter adapter = new SimpleAdapter(
                        context, serverList,
                        R.layout.list_item, new String[]{"name", "distance"},
                        new int[]{R.id.name, R.id.distance}) {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        if(position == 0)
                            view.setBackgroundResource(R.drawable.main_header_selector);
                        else
                            view.setBackgroundResource(0);
                        return view;
                    }
                };

                listView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
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
        Volley.newRequestQueue(context).add(stringRequest);
    }

}
