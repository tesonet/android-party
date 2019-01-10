package lt.zilinskas.marius.testio.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import lt.zilinskas.marius.testio.R;
import lt.zilinskas.marius.testio.activities.ServersActivity;
import lt.zilinskas.marius.testio.api.entities.Server;
import lt.zilinskas.marius.testio.api.entities.Token;

import static lt.zilinskas.marius.testio.api.RetrofitClient.AUTHORIZATION_METHOD;

public class LoadingFragment extends BaseFragment {

    private CompositeDisposable compositeDisposable;

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
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadServers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    private void loadServers() {
        Token token = testioApplication.getSharedPreferences().getToken();

        if (token == null || token.getToken().isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.unexpected_issue_occured), Toast.LENGTH_LONG).show();
            return;
        }

        Disposable disposable = testioApplication.getPlaygroundApi()
                .getServers(AUTHORIZATION_METHOD + " " + token.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());
        compositeDisposable.add(disposable);
    }

    private DisposableObserver<List<Server>> getObserver() {
        return new DisposableObserver<List<Server>>() {
            @Override
            public void onNext(List<Server> servers) {
                saveServers(servers);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getContext(), getString(R.string.unable_to_fetch_servers), Toast.LENGTH_LONG).show();
                // Delete users token and return user to login screen to try again
                startLoginFragment();
            }

            @Override
            public void onComplete() {
            }
        };
    }

    private void saveServers(List<Server> servers) {
        Disposable disposable = Completable
                .fromAction(() ->
                        testioApplication.getDatabaseHelper().getServersDAO().insertOrReplace(servers, true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::startServersActivity);
        compositeDisposable.add(disposable);
    }

    private void startServersActivity() {
        if (getContext() == null || getActivity() == null) {
            return;
        }

        Intent intent = new Intent(getContext(), ServersActivity.class);
        getContext().startActivity(intent);
        getActivity().finish();
    }

    private void startLoginFragment() {
        if (getActivity() == null) {
            return;
        }

        testioApplication.getSharedPreferences().setToken(null);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment(), LoginFragment.class.getName())
                .commit();
    }
}
