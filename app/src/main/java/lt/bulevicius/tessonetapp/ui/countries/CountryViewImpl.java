package lt.bulevicius.tessonetapp.ui.countries;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lt.bulevicius.tessonetapp.R;
import lt.bulevicius.tessonetapp.app.TessonetApplication;
import lt.bulevicius.tessonetapp.network.entities.data.Country;
import lt.bulevicius.tessonetapp.ui.BaseView;
import lt.bulevicius.tessonetapp.ui.countries.adapters.CountryAdapter;

public final class CountryViewImpl extends BaseView implements CountryView {

    @Inject
    CountryPresenter countryPresenter;

    @Inject
    CountryAdapter countryAdapter;

    @BindView(R.id.logOutButton)
    ImageButton imageButton;

    @BindView(R.id.countryListRecyclerView)
    RecyclerView recyclerView;

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.view_countries, container, false);
    }

    @Override
    public void doInject() {
        TessonetApplication.getComponent().inject(this);
    }

    @Override
    public void doBindViews(View view) {
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        countryPresenter.setView(this);
        countryPresenter.getCountries();
    }

    @Override
    protected void onDetach(@NonNull View view) {
        countryPresenter.setView(null);
        super.onDetach(view);
    }

    @Override
    public void onItems(List<Country> countries) {
        countryAdapter.setItems(countries);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
