package com.azzdorfrobotics.android.testio.data;

import android.support.annotation.NonNull;
import com.azzdorfrobotics.android.testio.network.model.response.Server;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created on 03.01.2018.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

@Singleton public class ServerRepository {

    private final RealmConfiguration realmConfiguration;

    @Inject public ServerRepository(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    public void clear() {
        execute(realm -> {
            realm.where(Server.class).findAll().deleteAllFromRealm();
        });
    }

    private void put(List<Server> items) {
        execute(realm -> realm.copyToRealm(items));
    }

    public void deleteAndPut(List<Server> items) {
        clear();
        put(items);
    }

    public Observable<RealmResults<Server>> queryAll() {
        return getRealmAsObservable()
            .flatMap(realm -> realm.where(Server.class)
                .findAllAsync()
                .asFlowable()
                .toObservable()
                .filter(RealmResults::isLoaded)
                .doOnDispose(realm::close));
    }

    @NonNull private Realm getRealm() {
        return Realm.getInstance(realmConfiguration);
    }

    private void execute(Realm.Transaction transaction) {
        Realm realm = getRealm();
        try {
            realm.executeTransaction(transaction);
        } finally {
            realm.close();
        }
    }

    @NonNull private Observable<Realm> getRealmAsObservable() {
        return Observable.defer(() -> Observable.just(getRealm()));
    }
}
