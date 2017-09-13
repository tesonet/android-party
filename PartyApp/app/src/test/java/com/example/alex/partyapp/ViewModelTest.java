package com.example.alex.partyapp;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.alex.partyapp.data.ServersRepository;
import com.example.alex.partyapp.loading.LoadingViewModel;
import com.example.alex.partyapp.login.LoginViewModel;
import com.example.alex.partyapp.servers.ServersViewModel;
import com.example.alex.partyapp.utils.SingleLiveEvent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ViewModelTest {
    private static final String USERNAME = "login";
    private static final String PASSWORD = "password";

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private ServersRepository repository;
    private ServersViewModel serversViewModel;
    private LoadingViewModel loadingViewModel;
    private LoginViewModel loginViewModel;


    @Before
    public void setup() {
        repository = mock(ServersRepository.class);
        when(repository.getStatus()).thenReturn(new SingleLiveEvent<>());
        when(repository.getError()).thenReturn(new SingleLiveEvent<>());
        when(repository.getServers()).thenReturn(new SingleLiveEvent<>());

        serversViewModel = new ServersViewModel(repository);
        loadingViewModel = new LoadingViewModel(repository);
        loginViewModel = new LoginViewModel(repository);
    }


    @Test
    public void testNull() {
        //all ViewModels are too simple
        assertThat(serversViewModel.getServers(), notNullValue());
        assertThat(serversViewModel.getError(), notNullValue());
        assertThat(serversViewModel.getStatus(), notNullValue());
    }

    @Test
    public void testLogin() {
        loginViewModel.login(USERNAME, PASSWORD);
        verify(repository, atLeastOnce()).login(anyString(), anyString());
    }

    @Test
    public void testLogout() {
        serversViewModel.logout();
        verify(repository, atLeastOnce()).logout();
    }

    @Test
    public void testLoad(){
        loadingViewModel.load();
        verify(repository, atLeastOnce()).load();
    }


}