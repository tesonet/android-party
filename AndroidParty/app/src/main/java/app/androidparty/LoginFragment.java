package app.androidparty;


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.JsonObject;

import app.androidparty.Helpers.ToastsHandler;
import app.androidparty.Services.ApiService;
import app.androidparty.databinding.FragmentLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private View view;
    private EditText usernameField;
    private EditText passwordField;
    private MainActivity activity;
    private ApiService apiService;
    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity)getActivity();
        apiService = activity.getApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setHandlers(this);
        view = binding.getRoot();

        usernameField = (EditText) view.findViewById(R.id.userName);
        passwordField = (EditText) view.findViewById(R.id.userPsw);

        return view;
    }

    public void onDoLoginClick(){
        activity.hideKeyboard();
        String userName = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        if(isValid(userName,password)){
            activity.setIsLoading(true);
            Call<JsonObject> call = apiService.getToken(userName, password);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    int code = response.code();
                    if(code==200){
                        boolean parsed = parseToken(response.body());
                        if(parsed) {
                            clearCredentials();
                            activity.setMode(MainActivity.Mode.SERVERS_LIST);
                        }
                    }else{
                        activity.setIsLoading(false);
                        try{
                            String error = response.errorBody().string();
                            ToastsHandler.showMessage(view.getContext(),error);
                        }catch (Exception ex){
                            ToastsHandler.showMessage(view.getContext(),getResources().getText(R.string.login_error));
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    activity.setIsLoading(false);
                    ToastsHandler.showMessage(view.getContext(),getResources().getText(R.string.login_error));
                }
            });
        }else{
            ToastsHandler.showMessage(view.getContext(),getResources().getText(R.string.invalid_credentials));
        }

    }

    private boolean parseToken(JsonObject response){
        boolean success = true;
        try{
            String token = response.get("token").getAsString();
            activity.setToken(token);
        }catch (Exception ex){
            ToastsHandler.showMessage(view.getContext(),getResources().getText(R.string.failed_get_token));
            success = false;
        }
        return  success;
    }

    private boolean isValid(String userName, String password){
        boolean valid = false;
        if(userName.trim().length()>0 && password.trim().length()>0){
            valid = true;
        }
        return valid;
    }

    private void clearCredentials(){
        usernameField.getText().clear();
        passwordField.getText().clear();
    }

}
