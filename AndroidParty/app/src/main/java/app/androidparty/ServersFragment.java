package app.androidparty;


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.androidparty.Helpers.ToastsHandler;
import app.androidparty.Models.Server;
import app.androidparty.Services.ApiService;
import app.androidparty.databinding.FragmentServersBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServersFragment extends Fragment {

    private View view;
    private MainActivity activity;
    private ApiService apiService;
    private FragmentServersBinding binding;
    private ObservableArrayList<Server> serversList;

    public ServersFragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_servers, container, false);
        binding.setHandlers(this);
        view = binding.getRoot();
        loadServers();

        return view;
    }

    private void loadServers(){
        String token = activity.getToken();
        Call<ObservableArrayList<Server>> call = apiService.getServers(token);
        call.enqueue(new Callback<ObservableArrayList<Server>>() {
            @Override
            public void onResponse(Call<ObservableArrayList<Server>> call, Response<ObservableArrayList<Server>> response) {
                int code = response.code();
                if(code==200){
                    serversList = response.body();
                    binding.setServers(serversList);
                    activity.setIsLoading(false);
                }else{
                    activity.setIsLoading(false);
                    try{
                        String error = response.errorBody().string();
                        ToastsHandler.showMessage(view.getContext(),error);
                    }catch (Exception ex){
                        ToastsHandler.showMessage(view.getContext(),getResources().getText(R.string.failed_get_servers));
                    }
                }
            }

            @Override
            public void onFailure(Call<ObservableArrayList<Server>> call, Throwable t) {
                activity.setIsLoading(false);
                ToastsHandler.showMessage(view.getContext(),getResources().getText(R.string.failed_get_servers));
            }

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activity.setHeaderVisibility(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        activity.hideKeyboard();
    }
}
