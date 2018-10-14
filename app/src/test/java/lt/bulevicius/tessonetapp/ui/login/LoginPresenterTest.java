package lt.bulevicius.tessonetapp.ui.login;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import lt.bulevicius.tessonetapp.app.utils.NetworkSchedulerProvider;
import lt.bulevicius.tessonetapp.app.utils.SchedulerProvider;
import lt.bulevicius.tessonetapp.network.TessonetApi;
import lt.bulevicius.tessonetapp.network.entities.auth.TokenResponse;
import lt.bulevicius.tessonetapp.network.entities.data.Country;
import lt.bulevicius.tessonetapp.network.models.AuthModel;
import lt.bulevicius.tessonetapp.network.models.CountryModel;
import lt.bulevicius.tessonetapp.storage.LocalDataProvider;
import lt.bulevicius.tessonetapp.ui.error.AuthException;
import lt.bulevicius.tessonetapp.ui.error.ErrorHandlerImpl;
import retrofit2.HttpException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {

    private LoginPresenter loginPresenter;

    AuthModel authModel;
    CountryModel countryModel;

    @Mock
    LocalDataProvider localDataProvider;
    ErrorHandlerImpl errorHandler;
    @Mock
    LoginView loginView;
    @Mock
    TessonetApi tessonetApi;
    @Mock
    Context context;

    TestScheduler testScheduler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        errorHandler = new ErrorHandlerImpl(context);
        testScheduler = new TestScheduler();
        SchedulerProvider schedulerProvider = new NetworkSchedulerProvider(testScheduler, testScheduler);
        authModel = new AuthModel(tessonetApi, schedulerProvider);
        countryModel = new CountryModel(tessonetApi, schedulerProvider, localDataProvider);
        loginPresenter = new LoginPresenter(authModel, countryModel, localDataProvider, errorHandler);
        loginPresenter.setView(loginView);
    }

    @Test
    public void testLoginSuccess() {
        final String token = "token";
        final List<Country> countries = getTestCountries();
        doReturn(Observable.just(new TokenResponse(token))).when(tessonetApi).login(any());
        doReturn(Observable.just(countries)).when(tessonetApi).getCountries(any());
        loginPresenter.doLogin("", "");
        testScheduler.triggerActions();
        verify(loginView, times(1)).showProgress();
        verify(loginView, times(1)).loginSuccess();
        verify(localDataProvider, times(1)).setToken(token);
        verify(localDataProvider, times(1)).setCountries(countries);
        verify(loginView, times(1)).hideProgress();
        verify(loginView, times(1)).onDataSuccess();
    }

    @Test
    public void testErrorLogin() {
        HttpException exception = Mockito.mock(HttpException.class);
        doReturn(403).when(exception).code();
        String expectedValue = "auth";
        doReturn(expectedValue).when(context).getString(anyInt());
        doReturn(Observable.error(exception)).when(tessonetApi).login(any());
        loginPresenter.doLogin("", "");
        testScheduler.triggerActions();
        verify(loginView, times(1)).showProgress();
        ArgumentCaptor<Exception> exceptionArgumentCaptor = ArgumentCaptor.forClass(Exception.class);
        verify(loginView, times(1)).onError(exceptionArgumentCaptor.capture());
        assertEquals(1, exceptionArgumentCaptor.getAllValues().size());
        assertTrue(exceptionArgumentCaptor.getValue() instanceof AuthException);
    }

    private List<Country> getTestCountries() {
        return new ArrayList<Country>() {{
            add(new Country("test", 123));
        }};
    }

}