package com.tzemaitis.androidparty;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingFragment extends Fragment {
    private static final String AUTHENTICATION_TOKEN = "authentication_token";
    private static final String TOKEN_PREFIX = "Bearer ";

    private String mAuthenticationToken;

    private LoadingFragmentListener mListener;

    public LoadingFragment() {
    }

    public static LoadingFragment newInstance(String authenticationToken) {
        LoadingFragment fragment = new LoadingFragment();
        Bundle args = new Bundle();
        args.putString(AUTHENTICATION_TOKEN, authenticationToken);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAuthenticationToken = getArguments().getString(AUTHENTICATION_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        RotateAnimation animation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        getView().findViewById(R.id.indicator).startAnimation(animation);

        PartyApplication app = (PartyApplication) getActivity().getApplication();
        Call<ArrayList<Server>> call = app.getRestApi().getServers(TOKEN_PREFIX + mAuthenticationToken);

        call.enqueue(new Callback<ArrayList<Server>>() {
            @Override
            public void onResponse(Call<ArrayList<Server>> call, Response<ArrayList<Server>> response) {
                mListener.onLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Server>> call, Throwable t) {
                call.cancel();
                // TODO send user friendly error message in case if we failed to load server list
                mListener.onLoadFailed(t.getMessage());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoadingFragmentListener) {
            mListener = (LoadingFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface LoadingFragmentListener {
        // TODO: Update argument type and name
        void onLoaded(ArrayList<Server> servers);
        void onLoadFailed(String message);
    }
}
