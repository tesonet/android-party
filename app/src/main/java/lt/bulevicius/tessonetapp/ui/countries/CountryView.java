package lt.bulevicius.tessonetapp.ui.countries;

import java.util.List;

import lt.bulevicius.tessonetapp.network.entities.data.Country;
import lt.bulevicius.tessonetapp.ui.TessoView;

/**
 * The interface Country view.
 */
public interface CountryView extends TessoView {

    /**
     * On items.
     *
     * @param countries the countries
     */
    void onItems(List<Country> countries);
}
