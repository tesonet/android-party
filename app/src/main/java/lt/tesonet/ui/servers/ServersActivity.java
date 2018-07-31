package lt.tesonet.ui.servers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import lt.tesonet.R;
import lt.tesonet.model.DatabaseUtil;
import lt.tesonet.model.Server;
import lt.tesonet.ui.login.LogInActivity;

public class ServersActivity extends AppCompatActivity {

    @BindView(R.id.server_list)
    RecyclerView list;
    @BindView(R.id.empty_text)
    TextView emptyText;

    private ServersAdapter adapter;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servers);
        ButterKnife.bind(this);

        // init list
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        adapter = new ServersAdapter();
        list.setAdapter(adapter);
        list.addItemDecoration(divider);

        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.enter_new, R.anim.exit_old);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_old, R.anim.exit_new);
    }

    private void showNoData() {
        list.setVisibility(View.GONE);
        emptyText.setVisibility(View.VISIBLE);
    }

    private void fillListWithData(List<Server> servers) {
        adapter.setData(servers);
        adapter.notifyDataSetChanged();
    }

    private void loadData() {
        compositeDisposable.add(DatabaseUtil.getServerDatabase(this).getDao()
                .getAllServers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(servers -> {
                    if (servers.size() > 0) {
                        fillListWithData(servers);
                    } else {
                        showNoData();
                    }
                }, throwable -> showNoData()));
    }

    @OnClick(R.id.log_out)
    public void onLogOutClicked() {
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);
        finish();
    }

}
