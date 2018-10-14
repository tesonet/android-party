package lt.bulevicius.tessonetapp.network.models;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import lt.bulevicius.tessonetapp.app.Utils;
import lt.bulevicius.tessonetapp.app.utils.SchedulerProvider;
import lt.bulevicius.tessonetapp.network.TessonetApi;
import lt.bulevicius.tessonetapp.network.entities.data.Country;
import lt.bulevicius.tessonetapp.storage.LocalDataProvider;
import timber.log.Timber;

/**
 * The type Country model.
 */
public class CountryModel {

    private final TessonetApi api;
    private final SchedulerProvider schedulerProvider;
    private final LocalDataProvider localDataProvider;

    /**
     * Instantiates a new Country model.
     *
     * @param api               the api
     * @param schedulerProvider the scheduler provider
     * @param localDataProvider the local data provider
     */
    @Inject
    public CountryModel(TessonetApi api, SchedulerProvider schedulerProvider, LocalDataProvider localDataProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
        this.localDataProvider = localDataProvider;
    }

    /**
     * Gets country list.
     *
     * @return the country list
     */
    public Observable<List<Country>> getCountryList() {
        return api.getCountries(Utils.addBearrer(localDataProvider.getToken()))
                  .compose(schedulerProvider.applySchedulers());
    }
}
