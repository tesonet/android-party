package com.tzemaitis.androidparty;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ServerListFragment extends Fragment {

    private static final String SERVERS = "servers";
    private ServerListFragmentListener mListener;

    private ArrayList<Server> mServers;

    public ServerListFragment() {
    }

    public static ServerListFragment newInstance(ArrayList<Server> servers) {
        // HACK
        ServerListFragment fragment = new ServerListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(SERVERS, servers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mServers = getArguments().getParcelableArrayList(SERVERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_server_list, container, false);
        RecyclerView list = view.findViewById(R.id.list);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        list.addItemDecoration(itemDecorator);
        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
        list.setAdapter(new ServersRecyclerViewAdapter(mServers));

        view.findViewById(R.id.button_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLogOut();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ServerListFragmentListener) {
            mListener = (ServerListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface ServerListFragmentListener {
        // TODO: Update argument type and name
        void onLogOut();
    }
}
