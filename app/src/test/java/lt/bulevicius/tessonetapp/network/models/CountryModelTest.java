package lt.bulevicius.tessonetapp.network.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import lt.bulevicius.tessonetapp.app.utils.NetworkSchedulerProvider;
import lt.bulevicius.tessonetapp.network.TessonetApi;
import lt.bulevicius.tessonetapp.network.entities.data.Country;
import lt.bulevicius.tessonetapp.storage.LocalDataProvider;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class CountryModelTest {

    private TestScheduler testScheduler;
    @SuppressWarnings("all")
    @Mock
    TessonetApi tessonetApi;

    @Mock
    LocalDataProvider localDataProvider;

    private CountryModel countryModel;

    @Before
    public void setUp() {
        testScheduler = new TestScheduler();
        NetworkSchedulerProvider mockSchedulerProvider = new NetworkSchedulerProvider(testScheduler, testScheduler);
        MockitoAnnotations.initMocks(this);
        countryModel = new CountryModel(tessonetApi, mockSchedulerProvider, localDataProvider);
    }

    @Test
    public void testGetCountries() {
        doReturn(Observable.just(getTestCountries())).when(tessonetApi).getCountries(any());
        doReturn("token").when(localDataProvider).getToken();
        TestObserver<List<Country>> testObserver = new TestObserver<>();
        countryModel.getCountryList().subscribe(testObserver);
        testScheduler.triggerActions();
        testObserver.assertValue(list -> list.size() == 1);
    }

    private List<Country> getTestCountries() {
        return new ArrayList<Country>() {{
            add(new Country("test", 123));
        }};
    }
}