package lt.bulevicius.tessonetapp.ui.countries.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lt.bulevicius.tessonetapp.R;
import lt.bulevicius.tessonetapp.app.Utils;
import lt.bulevicius.tessonetapp.network.entities.data.Country;

/**
 * The type Country adapter.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> items;

    /**
     * Instantiates a new Country adapter.
     */
    @Inject
    @SuppressWarnings("all")
    public CountryAdapter() {

    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<Country> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                                      .inflate(R.layout.item_country, viewGroup, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int i) {
        Country country = items.get(i);
        countryViewHolder.countryName.setText(country.getName());
        countryViewHolder.distance.setText(Utils.addKilometers(country.getDistance()));
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    /**
     * The type Country view holder.
     */
    class CountryViewHolder extends RecyclerView.ViewHolder {

        /**
         * The Country name textView.
         */
        @BindView(R.id.countryName)
        AppCompatTextView countryName;

        /**
         * The Distance textView.
         */
        @BindView(R.id.countryDistance)
        AppCompatTextView distance;

        /**
         * Instantiates a new Country view holder.
         *
         * @param itemView the item view
         */
        CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
