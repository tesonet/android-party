package dev.task.dainius.androidparty;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    private String token;
    private List<Server> serverList = new ArrayList<>();
    private RequestQueue mRequestQueue;
    private static DataManager mInstance;

    public DataManager (Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
    }

    public static synchronized DataManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataManager(context);
        }
        return mInstance;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public interface VolleyCallback{
        void onSuccess(List<Server> result);
    }

    public void getServerList(final VolleyCallback callback) {
//        if(serverList.size() > 0) {
//            return serverList;
//        }
        serverList = new ArrayList<>();
        String url = "http://playground.tesonet.lt/v1/servers";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i < jsonArray.length(); i++) {
                        String name = jsonArray.getJSONObject(i).getString("name");
                        int distance = jsonArray.getJSONObject(i).getInt("distance");

                        serverList.add(new Server(name, distance));

                        Log.i("TESONET", "Server " + name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.i("TESONET", "Layers loaded");
                callback.onSuccess(serverList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.i("TESONET", "Request error: " + error.getMessage());

                callback.onSuccess(new ArrayList<Server>());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();

                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        mRequestQueue.add(stringRequest);
    }
}
