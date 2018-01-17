package lajevski.radoslav.tesonetparty.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import lajevski.radoslav.tesonetparty.data.network.ApiHelper;
import lajevski.radoslav.tesonetparty.data.network.model.LoginResponse;
import lajevski.radoslav.tesonetparty.data.network.model.Server;
import lajevski.radoslav.tesonetparty.data.network.model.User;
import lajevski.radoslav.tesonetparty.data.preferences.PreferencesHelper;

/**
 * Created by Radoslav on 1/16/2018.
 */

@Singleton
public class AppDataManager implements DataManager {

    private final PreferencesHelper mPreferencesHelper;

    private final ApiHelper mApiClienHelper;

    @Inject
    public AppDataManager(PreferencesHelper preferencesHelper, ApiHelper apiClientHelper) {
        mPreferencesHelper = preferencesHelper;
        mApiClienHelper = apiClientHelper;
    }

    @Override
    public String getToken() {
        return mPreferencesHelper.getToken();
    }

    @Override
    public void setToken(String token) {
        mPreferencesHelper.setToken(token);
    }

    @Override
    public Observable<LoginResponse> getToken(User user) {
        return mApiClienHelper.getToken(user);
    }

    @Override
    public Observable<List<Server>> getServers(String token) {
      return   mApiClienHelper.getServers(token);
    }

}
