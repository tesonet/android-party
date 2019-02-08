package com.example.partyapp;


import android.os.AsyncTask;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetServersTask extends AsyncTask<String, Void, ArrayList<Server>> {

    private static String baseUrl = "http://playground.tesonet.lt/v1/";
    private String token;

    public interface AsyncResponse {
        void processFinish(ArrayList<Server> serverList);
    }

    public AsyncResponse delegate = null;

    public GetServersTask(String token, AsyncResponse delegate) {
        this.delegate = delegate;
        this.token = token;
    }

    @Override
    protected void onPostExecute(ArrayList<Server> serverList) {
        delegate.processFinish(serverList);
    }

    @Override
    protected ArrayList<Server> doInBackground(String... args) {
        try {


            URL url = new URL(baseUrl + "servers");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("GET");
            httpCon.setRequestProperty("Authorization", "Bearer " + token);
//            httpCon.setRequestProperty("Content-Type", "application/json; charset=utf-8");

//            OutputStream os = null;
//            os = httpCon.getOutputStream();
//            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//            JSONObject body = new JSONObject();
//            try {
//                body.put("username", args[0]);
//                body.put("password", args[1]);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            osw.write(body.toString());
//            osw.flush();
//            osw.close();
//            os.close();  //don't forget to close the OutputStream
            Map<String, List<String>> headerFields = httpCon.getHeaderFields();

            httpCon.connect();

            //read the inputstream and print it
            String result;
            int code = httpCon.getResponseCode();
            String msg = httpCon.getResponseMessage();
            InputStream is = httpCon.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            ByteArrayOutputStream buf = new ByteArrayOutputStream();
//            int result2 = bis.read();
//            while (result2 != -1) {
//                buf.write((byte) result2);
//                result2 = bis.read();
//            }

            return parseJson(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Server> parseJson(InputStream inputStream) {
        ArrayList<Server> serverList = new ArrayList<>();
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                serverList.add(readMessage(jsonReader));
            }
            jsonReader.endArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverList;
    }

    private Server readMessage(JsonReader reader) {
        String name ="";
        int distance = -1;
        try {

            reader.beginObject();
            while (reader.hasNext()) {
                String key = reader.nextName();
                if (key.equals("name")) {
                    name = reader.nextString();
                } else if (key.equals("distance")) {
                    distance = reader.nextInt();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Server(name, distance);
    }
}
