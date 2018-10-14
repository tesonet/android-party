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
     * @param countryModel the country model
     */
    @Inject
    public CountryPresenter(CountryModel countryModel, LocalDataProvider localDataProvider, ErrorHandlerImpl errorHandler) {
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
                                      .subscribe(items -> getView().onItems(items), e -> {
                                          getView().hideProgress();
                                          getView().onError(errorHandler.handleError(e));
                                      })
        );
    }

    void doLogout() {
        subscriptions.add(getLogoutObs().subscribe(it-> getView().logout()));
    }

    Observable<Boolean> getLogoutObs() {
        return Observable.fromCallable(() -> {
            localDataProvider.setToken(null);
            return true;
        });
    }
}
