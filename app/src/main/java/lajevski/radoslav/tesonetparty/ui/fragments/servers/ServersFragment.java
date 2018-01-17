package lajevski.radoslav.tesonetparty.ui.fragments.servers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lajevski.radoslav.tesonetparty.R;
import lajevski.radoslav.tesonetparty.data.network.model.Server;
import lajevski.radoslav.tesonetparty.di.ActivityComponent;
import lajevski.radoslav.tesonetparty.ui.activities.LoginActivity;
import lajevski.radoslav.tesonetparty.ui.base.BaseFragment;

/**
 * Created by Radoslav on 11/20/2017.
 */

public class ServersFragment extends BaseFragment implements ServersMvpView {

    @BindView(R.id.f_servers_loading_container) View mLoadingLayout;

    @BindView(R.id.f_servers_rv_servers)
    RecyclerView mRVServers;

    @Inject
    ServersPresenter<ServersMvpView> mPresenter;

    @Inject
    ServerAdapter mServerAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    public ServersFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static ServersFragment newInstance() {
        ServersFragment fragment = new ServersFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f_servers_list, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        ActivityComponent component = getActivityComponent();
        if (component != null) {

            component.inject(this);

            initViews();

            mPresenter.onAttach(this);

            mPresenter.onViewPrepared();
        }
        return view;
    }

    public void initViews() {

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRVServers.setLayoutManager(mLayoutManager);

        mRVServers.setItemAnimator(new DefaultItemAnimator());

        mRVServers.setAdapter(mServerAdapter);
    }

    @Override
    public void updateServersList(List<Server> serverList) {
        mServerAdapter.addItems(serverList);
    }

    @Override
    public void switchContent(Fragment fragment, String fragmentTag) {
        super.switchContent(fragment, fragmentTag);
    }

    @Override
    public void hideToolbar() {
        if (getToolbar() != null) {
            getToolbar().setVisibility(View.GONE);
        }
    }

    @Override
    public void showToolbar() {
        if (getToolbar() != null) {
            getToolbar().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoading() {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoadingLayout.animate().alpha(0.0f).setDuration(350).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mPresenter.loadingAnimationEnded();
                mLoadingLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_logout:
                mPresenter.logOutClicked();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(getActivity());
        startActivity(intent);
        getActivity().finish();
    }
}
