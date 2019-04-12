package lt.liutkevicius.tesonetandroidparty.ui.servers;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import lt.liutkevicius.tesonetandroidparty.PartyApp;
import lt.liutkevicius.tesonetandroidparty.R;
import lt.liutkevicius.tesonetandroidparty.storage.SharedPrefs;
import lt.liutkevicius.tesonetandroidparty.ui.base.BaseView;
import lt.liutkevicius.tesonetandroidparty.ui.login.LoginViewImpl;

import javax.inject.Inject;

public class ServersViewImpl extends BaseView implements ServersView {

    @BindView(R.id.servers_list)
    RecyclerView recyclerView;

    @Inject
    ServersPresenter presenter;

    @Inject
    SharedPrefs sharedPrefs;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.screen_servers, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        presenter.setView(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.divider_server_list);
        dividerItemDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ServersAdapter adapter = new ServersAdapter(sharedPrefs.getServers());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void doInjection() {
        PartyApp.getAppComponent().inject(this);
    }

    @OnClick(R.id.logout_button)
    public void onLogoutClicked() {
        presenter.logout();
    }

    @Override
    public void showLogin() {
        getRouter().setRoot(RouterTransaction.with(new LoginViewImpl())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }
}
