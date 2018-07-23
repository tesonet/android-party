package com.njakawaii.tesonet;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.njakawaii.tesonet.data.ServerModel;
import com.njakawaii.tesonet.data.ServersDAO;
import com.njakawaii.tesonet.data.ServersDB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ServersDBTest {
    private ServersDAO serversDAO;
    private ServersDB mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, ServersDB.class).build();
        serversDAO = mDb.serverDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        ServerModel serverModel = new ServerModel("qwe", 444);
        ServerModel serverModel2 = new ServerModel("qwe1", 4441);
        ServerModel serverModel3 = new ServerModel("qwe2", 4442);
        List<ServerModel> models = new ArrayList<>();
        models.add(serverModel);
        models.add(serverModel2);
        models.add(serverModel3);
        serversDAO.insertAll(models);
        serversDAO.getAll()
        .subscribeOn(Schedulers.io())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serverModels -> assertThat(serverModels.size(), equalTo(3)));
                }
}