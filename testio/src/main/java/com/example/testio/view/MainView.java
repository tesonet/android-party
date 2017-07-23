package com.example.testio.view;

import android.support.annotation.UiThread;
import com.example.testio.models.Server;
import java.util.List;

@UiThread
public interface MainView {

  void getServers(List<Server> serverList);

  void onError();

  void noToken();
}