package com.example.andrey.teso.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.andrey.teso.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrey Staniukynas
 */
public class MainActivity extends AppCompatActivity {

    private String URL = "http://playground.tesonet.lt/v1/tokens";
    private EditText etName, etPass;
    private Button btn;
    private String token;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etusername);
        etPass = findViewById(R.id.etpassword);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username");
            password = intent.getStringExtra("password");
            etName.setText(username);
            etPass.setText(password);
        }
    }

    private void loginUser() {
        username = etName.getText().toString().trim();
        password = etPass.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        parseData(response);
                        launchActivity();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.toString().equals("com.android.volley.AuthFailureError")) {
                            Toast.makeText(MainActivity.this, "Wrong username/password", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void parseData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            token = jsonObject.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void launchActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("token", token);
        startActivity(intent);
    }

}
