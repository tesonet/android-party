package app.androidparty;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import app.androidparty.Helpers.ToastsHandler;
import app.androidparty.Services.ApiService;
import app.androidparty.databinding.ActivityMainBinding;


public class MainActivity extends Activity {

    public enum Mode{
        LOGIN,
        SERVERS_LIST
    }

    private LoginFragment loginFragment;
    private ServersFragment serversFragment;
    private ApiService apiService;
    private FragmentManager fm;
    private String token;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandlers(this);
        setIsLoading(false);
        fm = getFragmentManager();
        apiService = ApiService.retrofit.create(ApiService.class);
        if(!isNetworkConnected()){
            ToastsHandler.showMessage(this, getString(R.string.no_internet));
        }else{
            setMode(Mode.LOGIN);
        }
    }

    public void setIsLoading(boolean isLoading){
        binding.setIsLoading(isLoading);
    }

    public void setMode(Mode action){
        FragmentTransaction transaction = fm.beginTransaction();
        if(action == Mode.LOGIN){
            loginFragment = new LoginFragment();
            transaction.replace(R.id.main_frame, loginFragment);
            transaction.commit();
            setHeaderVisibility(false);
        }else if(action == Mode.SERVERS_LIST){
            hideKeyboard();
            serversFragment = new ServersFragment();
            transaction.replace(R.id.main_frame, serversFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            setHeaderVisibility(true);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public void hideKeyboard(){
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return  "Bearer " + token;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public void doLogOut(){
        setToken(null);
        goBack();
    }

    public void goBack(){
        hideKeyboard();
        fm.popBackStackImmediate();
        setHeaderVisibility(false);
    }

    public void setHeaderVisibility(boolean visibility){
        binding.setIsHeaderVisible(visibility);
    }



}
