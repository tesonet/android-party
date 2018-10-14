package lt.bulevicius.tessonetapp.ui.countries;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lt.bulevicius.tessonetapp.R;
import lt.bulevicius.tessonetapp.app.TessonetApplication;
import lt.bulevicius.tessonetapp.network.entities.data.Country;
import lt.bulevicius.tessonetapp.ui.BaseView;
import lt.bulevicius.tessonetapp.ui.countries.adapters.CountryAdapter;
import lt.bulevicius.tessonetapp.ui.login.LoginViewImpl;

public final class CountryViewImpl extends BaseView implements CountryView {

    @Inject
    CountryPresenter countryPresenter;

    @Inject
    CountryAdapter countryAdapter;

    @SuppressWarnings("all")
    @BindView(R.id.notItemsLabel)
    AppCompatTextView noItemsLabel;

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
    public void logout() {
        getRouter().setRoot(RouterTransaction.with(new LoginViewImpl()));
    }

    @OnClick(R.id.logOutButton)
    public void onClick() {
        countryPresenter.doLogout();
    }

    @Override
    @SuppressWarnings("all")
    public void onError(Throwable error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        if(countryAdapter.getItemCount() == 0)
            noItemsLabel.setVisibility(View.VISIBLE);
        else
            noItemsLabel.setVisibility(View.GONE);
    }
}
