package com.example.alex.partyapp;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.alex.partyapp.data.Constants;
import com.example.alex.partyapp.data.Server;
import com.example.alex.partyapp.data.ServersRepository;
import com.example.alex.partyapp.data.ServersRepositoryImpl;
import com.example.alex.partyapp.data.ServersStorage;
import com.example.alex.partyapp.data.Token;
import com.example.alex.partyapp.data.TokenStorage;
import com.example.alex.partyapp.network.TesonetApi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.alex.partyapp.data.Constants.Status;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ServersRepositoryTest {
    private static final String USERNAME = "login";
    private static final String PASSWORD = "password";

    private TokenStorage tokenStorage;
    private ServersStorage serversStorage;
    private TesonetApi api;
    private ServersRepository repo;
    private Token token;
    private ArrayList testServers;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        tokenStorage = mock(TokenStorage.class);
        serversStorage = mock(ServersStorage.class);
        api = mock(TesonetApi.class);
        repo = new ServersRepositoryImpl(api, tokenStorage, serversStorage);
        token = mock(Token.class);
        when(token.getToken()).thenReturn("token");
        when(tokenStorage.load()).thenReturn("token");
        when(serversStorage.load()).thenReturn(null);

        testServers = new ArrayList<>();
        Server serverItem = mock(Server.class);
        when(serverItem.getName()).thenReturn("Name");
        when(serverItem.getDistance()).thenReturn("Distance");
        testServers.add(serverItem);
    }

    @Test
    public void login() {
        when(api.login(notNull())).thenReturn(createCall(Response.success(token)));
        repo.login(USERNAME, PASSWORD);
        assertThat(repo.getStatus().getValue(), is(Status.LOGGED_IN));
        assertThat(repo.getError().getValue(), is(Constants.Error.NONE));
    }

    @Test
    public void loginFailed(){
        when(api.login(notNull())).thenReturn(createCall(Response.success(null)));
        repo.login("login", "password");
        assertThat(repo.getStatus().getValue(), is(Status.LOGGED_OUT));
        assertThat(repo.getError().getValue(), is(Constants.Error.AUTHORIZATION_FAILED));
    }

    @Test
    public void loginOffline(){
        when(api.login(notNull())).thenReturn(createFailCall(null));
        repo.login("login", "password");
        assertThat(repo.getStatus().getValue(), is(Status.LOGGED_OUT));
        assertThat(repo.getError().getValue(), is(Constants.Error.NETWORK_ERROR));
    }

    @Test
    public void logout(){
        repo.logout();
        assertThat(repo.getStatus().getValue(), is(Status.LOGGED_OUT));
        assertThat(repo.getError().getValue(), is(Constants.Error.NONE));
    }

    @Test
    public void load(){
        when(api.getServers(notNull())).thenReturn(createCall(Response.success(testServers)));
        repo.load();
        assertThat(repo.getStatus().getValue(), is(Status.LOADED));
        assertThat(repo.getError().getValue(), is(Constants.Error.NONE));
        assertThat(repo.getServers().getValue(), is(testServers));
    }

    @Test
    public void loadOffline(){
        when(api.getServers(notNull())).thenReturn(createFailCall(null));
        repo.load();
        assertThat(repo.getStatus().getValue(), is(Status.LOGGED_IN));
        assertThat(repo.getError().getValue(), is(Constants.Error.NETWORK_ERROR));
    }

    @Test
    public void loadOfflineCached(){
        when(serversStorage.load()).thenReturn(testServers);
        when(api.getServers(notNull())).thenReturn(createFailCall(null));
        repo.load();
        assertThat(repo.getStatus().getValue(), is(Status.LOADED));
        assertThat(repo.getError().getValue(), is(Constants.Error.NETWORK_ERROR));
        assertThat(repo.getServers().getValue(), is(testServers));
    }

    @Test
    public void loadWrongToken(){
        when(api.getServers(notNull())).thenReturn(createCall(Response.error(HTTP_UNAUTHORIZED, emptyBody())));
        repo.load();
        assertThat(repo.getStatus().getValue(), is(Status.LOGGED_OUT));
        assertThat(repo.getError().getValue(), is(Constants.Error.AUTHORIZATION_FAILED));
    }

    @Test
    public void loadServerError(){
        when(api.getServers(notNull())).thenReturn(createCall(Response.error(HTTP_UNAVAILABLE, emptyBody())));
        repo.load();
        assertThat(repo.getStatus().getValue(), is(Status.LOGGED_OUT));
        assertThat(repo.getError().getValue(), is(Constants.Error.FAILED));
    }

    @Test
    public void loadNoToken(){
        when(tokenStorage.load()).thenReturn(null);
        repo.load();
        assertThat(repo.getStatus().getValue(), is(Status.LOGGED_OUT));
        assertThat(repo.getError().getValue(), is(Constants.Error.NONE));
    }

    @Test
    public void loadOnStart(){
        when(serversStorage.load()).thenReturn(testServers);
        repo = new ServersRepositoryImpl(api, tokenStorage, serversStorage);
        assertThat(repo.getStatus().getValue(), is(Status.LOADED));
        assertThat(repo.getError().getValue(), is(Constants.Error.OFFLINE_RESULTS));
        assertThat(repo.getServers().getValue(), is(testServers));
    }

    private ResponseBody emptyBody() {
       return new ResponseBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public long contentLength() {
                return 0;
            }

            @Override
            public BufferedSource source() {
                return null;
            }
        };
    }

    private <T> Call<T> createFailCall(Throwable throwable) {
        return new Call<T>() {
            @Override
            public Response<T> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<T> callback) {
                callback.onFailure(this, throwable);
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<T> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }
    private <T> Call<T> createCall(Response<T> response) {
        return new Call<T>() {
            @Override
            public Response<T> execute() throws IOException {
                return response;
            }

            @Override
            public void enqueue(Callback<T> callback) {
                    callback.onResponse(null, response);
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<T> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }
}
