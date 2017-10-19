package jv.noname.lt.androidparty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by inter on 10/12/2017.
 */

public class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECT_TIMEOUT = 15000;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON = "application/json";
    private static final String REQUEST_METHOD = "POST";
    private static final String CHARSET_NAME = "UTF-8";
    private static final String GET_AUTHORIZATION_REQ_PROP_KEY = "Authorization";
    private static final String GET_AUTHORIZATION_REQ_PROP_VAL = "Bearer %s";
    private static final String NAME = "name";
    private static final String DISTANCE = "distance";
    public static final String SERVER_LIST = "server_list";

    private ProgressDialog dialog;
    private Context context;
    private int advertisementId;
    HashMap<String, Integer> serverList = null;


    public RetrieveFeedTask(Context contex, int advertisementId) {

        this.context = contex;
        this.advertisementId = advertisementId;
        dialog = new ProgressDialog(contex);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage(context.getResources().getString(R.string.loadingServers));
        dialog.show();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        boolean status = true;
        String username = params[0];
        String password = params[1];
        String token = "";
        String json = "";

        try {
            token = performPostCall(params[2], username, password);
        } catch (Exception e) {
            status = false;
        }

        if (!token.equalsIgnoreCase("")) {
            try {
                json = performGetCall(params[3], token);
            } catch (JSONException e) {
                status = false;
            }
        } else {
            status = false;
        }

        serverList = jsonToMap(json);

        try {
            Thread.sleep(2000);/*2 see loading poperly. rly fast servers (: */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result && serverList != null) {
            Intent intent = new Intent(context, ServerListActivity.class);
            intent.putExtra(SERVER_LIST, serverList);
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((Activity) context).finish();
        } else {

        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public String performPostCall(String requestURL, String username, String password) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setRequestMethod(REQUEST_METHOD);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty(CONTENT_TYPE, JSON);

            JSONObject root = new JSONObject();
            root.put(USERNAME, username);
            root.put(PASSWORD, password);

            String str = root.toString();
            byte[] outputBytes = str.getBytes(CHARSET_NAME);
            OutputStream os = conn.getOutputStream();
            os.write(outputBytes);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
            JSONObject jRoot = new JSONObject(response);
            return (String) jRoot.get("token");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String performGetCall(String requestURL, String token) throws JSONException {
        URL url;
        String response = "";
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(requestURL);

            urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setRequestProperty(GET_AUTHORIZATION_REQ_PROP_KEY, String.format(GET_AUTHORIZATION_REQ_PROP_VAL, token));
            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                response += current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return response;
    }

    private HashMap<String, Integer> jsonToMap(String t) {
        HashMap<String, Integer> map = new HashMap<>();
        try {
            JSONArray jsonArray = new JSONArray(t);
            for (int i = 0; i < jsonArray.length(); i++) {
                String country = (String) jsonArray.getJSONObject(i).get(NAME);
                Integer distance = (Integer) jsonArray.getJSONObject(i).get(DISTANCE);
                map.put(country, distance);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return map;
    }
}
