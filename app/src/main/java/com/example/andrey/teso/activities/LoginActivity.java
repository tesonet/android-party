package com.example.andrey.teso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.andrey.teso.R;
import com.example.andrey.teso.servers.Server;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private String URL = "http://playground.tesonet.lt/v1/servers";
    private String token;
    private String username;
    private String password;
    ArrayList<Server> sList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        getData();
    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                        parseData(response);
                        launchActivity();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void parseData(String response) {
        try {
            JSONArray servers = new JSONArray(response);
            for (int i = 0; i < servers.length(); i++) {
                JSONObject server = new JSONObject(servers.getString(i));
                String name = server.getString("name");
                int serverId = Integer.parseInt(name.substring(name.indexOf("#") + 1, name.lastIndexOf("")));
                name = name.substring(0, name.indexOf("#")-1);
                int distance = Integer.parseInt(server.getString("distance"));
                sList.add(new Server(serverId, name, distance ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void launchActivity() {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putParcelableArrayListExtra("servers", sList);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }

}
