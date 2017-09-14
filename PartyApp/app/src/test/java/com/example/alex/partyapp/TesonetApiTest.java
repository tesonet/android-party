package com.example.alex.partyapp;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.alex.partyapp.data.Account;
import com.example.alex.partyapp.data.Server;
import com.example.alex.partyapp.data.Token;
import com.example.alex.partyapp.network.TesonetApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class TesonetApiTest {
    private static final String TEST_TOKEN = "f9731b590611a5a9377fbd02f247fcdf";

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private TesonetApi service;
    private MockWebServer mockWebServer;

    @Before
    public void createService() throws IOException {
        mockWebServer = new MockWebServer();
        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TesonetApi.class);
    }

    @After
    public void stopService() throws IOException {
        mockWebServer.shutdown();
    }

    private void enqueueResponse(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("api-response/" + fileName);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(Charset.defaultCharset())));
    }

    @Test
    public void getToken() throws IOException, InterruptedException {
        enqueueResponse("token.json");
        Token response = service.login(new Account("tesonet", "partyanimal")).execute().body();
        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getPath(), is("/tokens"));
        assertThat(response.getToken(), is(TEST_TOKEN));
    }

    @Test
    public void getServers() throws IOException, InterruptedException {
        enqueueResponse("servers.json");
        List<Server> response = service.getServers(TEST_TOKEN).execute().body();
        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getPath(), is("/servers"));
        assertThat(response.size(), not(0));
        for (Server server : response) {
            assertThat(server.getName(), notNullValue());
            assertThat(server.getDistance(), notNullValue());
        }
    }
}
