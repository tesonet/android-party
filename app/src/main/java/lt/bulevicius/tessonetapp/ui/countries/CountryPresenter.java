package lt.bulevicius.tessonetapp.ui.countries;

import lt.bulevicius.tessonetapp.network.models.CountryModel;
import lt.bulevicius.tessonetapp.ui.BasePresenter;

/**
 * The type Country presenter.
 */
public class CountryPresenter extends BasePresenter<CountryView> {

    private final CountryModel countryModel;

    /**
     * Instantiates a new Country presenter.
     *
     * @param countryModel the country model
     */
    public CountryPresenter(CountryModel countryModel) {
        this.countryModel = countryModel;
    }

    /**
     * Gets countries.
     */
    public void getCountries() {
        subscriptions.add(countryModel.getCountryList()
                                      .doOnSubscribe(d -> getView().showProgress())
                                      .subscribe(items -> getView().onItems(items), e -> {
                                          getView().hideProgress();
                                          getView().onError(e);
                                      })
        );
    }
}
