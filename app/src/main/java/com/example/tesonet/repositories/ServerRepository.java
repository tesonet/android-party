package com.example.tesonet.repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tesonet.database.ServerDatabase;
import com.example.tesonet.database.daos.ServerDao;
import com.example.tesonet.database.models.Server;
import com.example.tesonet.network.ApiInterface;
import com.example.tesonet.network.Service;

import java.util.List;

public class ServerRepository {
    private ServerDao serverDao;
    private LiveData<List<Server>> serverList;
    private Context context;
    private ApiInterface apiInterface;

    public ServerRepository(Application application) {
        apiInterface = Service.getRetrofitInstance().create(ApiInterface.class);
        context = application.getApplicationContext();
        ServerDatabase database = ServerDatabase.getInstance(application);
        serverDao = database.serverDao();
    }

    public void insert(Server server) {
        new ServerRepository.InsertServerAsyncTask(serverDao).execute(server);
    }

    public void deleteServers() {
        new ServerRepository.DeleteServerAsyncTask(serverDao).execute();
    }

    public LiveData<List<Server>> getAllServers(){
        return serverList;
    }

    private static class InsertServerAsyncTask extends AsyncTask<Server, Void, Void> {
        private ServerDao serverDao;

        private InsertServerAsyncTask(ServerDao serverDao) {
            this.serverDao = serverDao;
        }

        @Override
        protected Void doInBackground(Server... servers) {
            serverDao.insert(servers[0]);
            return null;
        }
    }

    private static class DeleteServerAsyncTask extends AsyncTask<Server, Void, Void> {
        private ServerDao serverDao;

        private DeleteServerAsyncTask(ServerDao serverDao) {
            this.serverDao = serverDao;
        }

        @Override
        protected Void doInBackground(Server... servers) {
            serverDao.deleteAllServers();
            return null;
        }
    }
}
