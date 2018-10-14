package lt.bulevicius.tessonetapp.network.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import lt.bulevicius.tessonetapp.app.utils.NetworkSchedulerProvider;
import lt.bulevicius.tessonetapp.network.TessonetApi;
import lt.bulevicius.tessonetapp.network.entities.auth.TokenRequest;
import lt.bulevicius.tessonetapp.network.entities.auth.TokenResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class AuthModelTest {

    private TestScheduler testScheduler;
    private AuthModel authModel;
    @SuppressWarnings("all")
    @Mock
    TessonetApi tessonetApi;

    @Before
    public void setUp() {
        testScheduler = new TestScheduler();
        NetworkSchedulerProvider mockSchedulerProvider = new NetworkSchedulerProvider(testScheduler, testScheduler);
        MockitoAnnotations.initMocks(this);
        authModel = new AuthModel(tessonetApi, mockSchedulerProvider);
    }

    @Test
    public void doLoginTest() {
        final String token = "Token!";
        final TokenResponse actual = new TokenResponse(token);
        doReturn(Observable.just(actual)).when(tessonetApi).login(any());
        TestObserver<TokenResponse> testObserver = new TestObserver<>();
        authModel.doLogin(new TokenRequest("", "")).subscribe(testObserver);
        testScheduler.triggerActions();
        testObserver.assertValue(response -> response.getToken().contentEquals(token));
    }
}