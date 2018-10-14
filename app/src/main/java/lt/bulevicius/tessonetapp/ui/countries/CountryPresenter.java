package lt.bulevicius.tessonetapp.ui.countries;


import javax.inject.Inject;

import io.reactivex.Observable;
import lt.bulevicius.tessonetapp.network.models.CountryModel;
import lt.bulevicius.tessonetapp.storage.LocalDataProvider;
import lt.bulevicius.tessonetapp.ui.BasePresenter;
import lt.bulevicius.tessonetapp.ui.error.ErrorHandlerImpl;

/**
 * The type Country presenter.
 */
public class CountryPresenter extends BasePresenter<CountryView> {

    private final CountryModel countryModel;
    private final ErrorHandlerImpl errorHandler;
    private final LocalDataProvider localDataProvider;

    /**
     * Instantiates a new Country presenter.
     *
     * @param countryModel      the country model
     * @param localDataProvider the local data provider
     * @param errorHandler      the error handler
     */
    @Inject
    CountryPresenter(CountryModel countryModel, LocalDataProvider localDataProvider, ErrorHandlerImpl errorHandler) {
        this.countryModel = countryModel;
        this.errorHandler = errorHandler;
        this.localDataProvider = localDataProvider;
    }

    /**
     * Gets countries.
     */
    void getCountries() {
        subscriptions.add(countryModel.getCountryList()
                                      .doOnSubscribe(d -> getView().showProgress())
                                      .subscribe(
                                              items -> getView().onItems(items),
                                              e -> {
                                                  getView().hideProgress();
                                                  getView().onError(errorHandler.handleError(e));
                                              },
                                              () -> getView().hideProgress())
        );
    }

    /**
     * Do logout.
     */
    void doLogout() {
        subscriptions.add(getLogoutObservable().subscribe(it -> getView().logout()));
    }

    /**
     * Gets logout observable.
     *
     * @return the logout observable
     */
    private Observable<Boolean> getLogoutObservable() {
        return Observable.fromCallable(() -> {
            localDataProvider.setToken(null);
            return true;
        });
    }
}
