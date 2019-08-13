package lt.tesonet.tesoapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import lt.tesonet.tesoapp.Models.Server;
import lt.tesonet.tesoapp.R;
import lt.tesonet.tesoapp.TesoApp;

public class LoginActivity extends AppCompatActivity {

    public static Context inst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inst=this;
        (findViewById(R.id.btnLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleLoading(true);
                Post();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ToggleLoading(false);
    }

    void ToggleLoading(boolean isLoading) {
        if(isLoading) {
            findViewById(R.id.fullViewLogin).setVisibility(View.GONE);
            findViewById(R.id.fullViewLoading).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.fullViewLogin).setVisibility(View.VISIBLE);
            findViewById(R.id.fullViewLoading).setVisibility(View.GONE);
        }
    }

    void OpenListActivity() {
        Intent intent=new Intent(this, lt.tesonet.tesoapp.Activities.ListActivity.class);
        startActivity(intent);
    }

    void GetList() {
        final Context ctx=inst;
        try {
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, TesoApp.serverUrl, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    TesoApp.serverList=new Gson().fromJson(response.toString(),new TypeToken<Server[]>(){}.getType());
                    OpenListActivity();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ctx,error.getMessage(),Toast.LENGTH_LONG).show();
                    ToggleLoading(false);
                }
            }) {
                @Override
                public Map getHeaders() {
                    HashMap headers = new HashMap();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "Bearer "+TesoApp.authToken);
                    return headers;
                }
            };
            TesoApp.getInstance().addToRequestQueue(jsonObjectRequest, "getRequest");
        }catch (Exception e) {
            Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_LONG).show();
            ToggleLoading(false);
        }
    }

    void Post() {
        final Context ctx=inst;
        try {
            JSONObject postparams=new JSONObject();
            postparams.put("username", ((EditText)findViewById(R.id.editUsername)).getText());
            postparams.put("password",((EditText)findViewById(R.id.editPassword)).getText());

            JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, TesoApp.tokenUrl, postparams,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //win
                            try {
                                TesoApp.authToken=response.getString("token");
                            }catch (Exception a) {
                                Toast.makeText(ctx,"Corrupt response.",Toast.LENGTH_LONG).show();
                            }
                            GetList();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //fail
                            Toast.makeText(ctx,"Login error.",Toast.LENGTH_LONG).show();
                            ToggleLoading(false);
                        }
                    }) {
                        @Override
                        public Map getHeaders() {
                            HashMap headers = new HashMap();
                            headers.put("Content-Type", "application/json");
                            return headers;
                        }
                    };
            TesoApp.getInstance().addToRequestQueue(jsonObjectRequest, "postRequest");
        }catch (Exception e) {
            Toast.makeText(ctx,e.getMessage(),Toast.LENGTH_LONG).show();
            ToggleLoading(false);
        }
    }
}
