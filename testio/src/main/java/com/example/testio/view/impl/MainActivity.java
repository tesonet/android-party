package com.example.testio.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.testio.R;
import com.example.testio.adapter.RecycleViewServersAdapter;
import com.example.testio.injection.AppComponent;
import com.example.testio.injection.DaggerMainViewComponent;
import com.example.testio.injection.MainViewModule;
import com.example.testio.models.Server;
import com.example.testio.presenter.MainPresenter;
import com.example.testio.presenter.loader.PresenterFactory;
import com.example.testio.view.MainView;
import java.util.List;
import javax.inject.Inject;

public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
  @Inject
  PresenterFactory<MainPresenter> mPresenterFactory;

  // Your presenter is available using the mPresenter variable

  @BindView(R.id.activity_main_root)
  View root;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.button_logout)
  ImageButton logout;

  @BindView(R.id.recyclerView)
  RecyclerView recyclerView;

  private RecycleViewServersAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    adapter = new RecycleViewServersAdapter();
    LinearLayoutManager layoutManager =
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    DividerItemDecoration dividerItemDecoration =
        new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
    recyclerView.addItemDecoration(dividerItemDecoration);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    logout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        releaseToken();
      }
    });
    // Your code here
    // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
  }

  private void releaseToken() {
    mPresenter.releaseToken();
  }

  private void goBackToSignIn() {
    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
    startActivity(intent);
    finish();
  }

  @Override
  protected void setupComponent(@NonNull AppComponent parentComponent) {
    DaggerMainViewComponent.builder()
        .appComponent(parentComponent)
        .mainViewModule(new MainViewModule())
        .build()
        .inject(this);
  }

  @NonNull
  @Override
  protected PresenterFactory<MainPresenter> getPresenterFactory() {
    return mPresenterFactory;
  }

  @Override
  public void getServers(List<Server> serverList) {
    adapter.changeData(serverList);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void onError() {
    Snackbar.make(root, "Server error", Snackbar.LENGTH_LONG);
  }

  @Override
  public void noToken() {
    goBackToSignIn();
  }
}
