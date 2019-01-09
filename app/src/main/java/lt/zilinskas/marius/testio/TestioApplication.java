package lt.zilinskas.marius.testio;

import android.app.Application;

import lombok.Getter;
import lt.zilinskas.marius.testio.api.PlaygroundApi;
import lt.zilinskas.marius.testio.api.RetrofitClient;
import lt.zilinskas.marius.testio.database.DatabaseHelper;
import lt.zilinskas.marius.testio.storage.SharedPreferencesHelper;

@Getter
public class TestioApplication extends Application {

    private SharedPreferencesHelper sharedPreferences;
    private PlaygroundApi playgroundApi;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        initSharedPreferences();
        initPlaygroundApi();
        initDatabaseHelper();
    }

    private void initSharedPreferences() {
        sharedPreferences = SharedPreferencesHelper.getInstance(this);
    }

    private void initPlaygroundApi() {
        playgroundApi = RetrofitClient.getInstance().create(PlaygroundApi.class);
    }

    private void initDatabaseHelper() {
        databaseHelper = DatabaseHelper.getInstance(this);
    }
}
