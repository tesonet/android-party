package com.example.testio.interactor;

import com.example.testio.models.Server;
import com.example.testio.models.Token;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

public interface MainInteractor extends BaseInteractor {

  Single<List<Server>> getServerList(String token);

  Single<Token> getToken();

  Completable releaseToken();
}