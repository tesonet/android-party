package com.example.partyapp.Tasks;


import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.partyapp.Models.Server;

import java.io.InputStream;
import java.io.InputStreamReader;
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

    public AsyncResponse delegate;

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
            //time to look at the loading dialog
            Thread.sleep(1000);
            URL url = new URL(baseUrl + "servers");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            httpCon.setRequestProperty("Authorization", "Bearer " + token);
            httpCon.connect();

            InputStream is = httpCon.getInputStream();
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
