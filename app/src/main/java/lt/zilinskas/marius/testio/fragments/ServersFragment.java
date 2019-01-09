package lt.zilinskas.marius.testio.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;
import lt.zilinskas.marius.testio.R;
import lt.zilinskas.marius.testio.adapters.ServersAdapter;
import lt.zilinskas.marius.testio.api.entities.Server;
import lt.zilinskas.marius.testio.database.DatabaseHelper;

public class ServersFragment extends BaseFragment {

    private CustomViewHolder customViewHolder;
    private CompositeDisposable compositeDisposable;
    private LoadedData loadedData;
    private ServersAdapter serversAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        compositeDisposable = new CompositeDisposable();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servers, container, false);
        customViewHolder = new CustomViewHolder(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    private void loadData() {
        Disposable disposable = Observable
                .just(testioApplication.getDatabaseHelper())
                .map(this::loadDataFromDb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::dataLoaded);
        compositeDisposable.add(disposable);
    }

    private LoadedData loadDataFromDb(DatabaseHelper databaseHelper) {
        loadedData = new LoadedData();
        loadedData.setServers(databaseHelper.getExpensesDAO().getAllServers());
        return loadedData;
    }

    private void dataLoaded(LoadedData loadedData) {
        this.loadedData = loadedData;
        populateAdapter();
    }

    private void populateAdapter() {
        if (serversAdapter != null && customViewHolder.serversList.getAdapter() != null) {
            serversAdapter.setServers(loadedData.servers);
            serversAdapter.notifyDataSetChanged();
            return;
        }

        initAdapter();
    }

    private void initAdapter() {
        if (getContext() == null) {
            return;
        }
        serversAdapter = new ServersAdapter(loadedData.servers);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        customViewHolder.serversList.setHasFixedSize(true);
        customViewHolder.serversList.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        customViewHolder.serversList.setLayoutManager(layoutManager);
        customViewHolder.serversList.setItemAnimator(new DefaultItemAnimator());
        customViewHolder.serversList.setAdapter(serversAdapter);
    }


    @Getter
    @Setter
    private class LoadedData {
        private List<Server> servers;
    }

    @Getter
    private class CustomViewHolder {
        private View parent;
        private RecyclerView serversList;

        CustomViewHolder(@NonNull View parent) {
            this.parent = parent;
            initViews();
        }

        private void initViews() {
            serversList = parent.findViewById(R.id.serversList);
        }
    }
}
