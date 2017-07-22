package com.example.testio.interactor;

import com.example.testio.models.Server;
import com.example.testio.models.Token;
import com.example.testio.models.User;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.util.List;

public interface LoginInteractor extends BaseInteractor {

  Observable<Token> getToken(User user);

  Observable<List<Server>> getServersList(String token);
}