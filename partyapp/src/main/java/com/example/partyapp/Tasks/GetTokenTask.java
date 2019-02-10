package com.example.partyapp.Tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetTokenTask extends AsyncTask<String, Void, String> {

    private static String baseUrl = "http://playground.tesonet.lt/v1/";

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public GetTokenTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }

    @Override
    protected String doInBackground(String... args) {
        try {


            URL url = new URL(baseUrl + "tokens");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            OutputStream os;
            os = httpCon.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            JSONObject body = new JSONObject();
            try {
                body.put("username", args[0]);
                body.put("password", args[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            osw.write(body.toString());
            osw.flush();
            osw.close();
            os.close();  //don't forget to close the OutputStream
            httpCon.connect();

            InputStream is = httpCon.getInputStream();
            return parseJson(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String parseJson(InputStream inputStream) {
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            jsonReader.beginObject();
            if (jsonReader.hasNext()) {
                String key = jsonReader.nextName();
                if (key.equals("token")) {
                    return jsonReader.nextString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
