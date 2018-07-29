package lt.tesonet;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.Snackbar;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import lt.tesonet.model.User;
import lt.tesonet.network.Api;
import lt.tesonet.model.Token;

import static lt.tesonet.LogInActivity.UIState.LOADING_LOG_IN;
import static lt.tesonet.LogInActivity.UIState.LOADING_SERVERS;
import static lt.tesonet.LogInActivity.UIState.LOG_IN;

public class LogInActivity extends AppCompatActivity {

    @IntDef({LOG_IN, LOADING_LOG_IN, LOADING_SERVERS})
    public @interface UIState {
        int LOG_IN = 0;
        int LOADING_LOG_IN = 1;
        int LOADING_SERVERS = 2;
    }

    @BindView(R.id.parent)
    ConstraintLayout parent;
    @BindView(R.id.log_in_username)
    EditText inputUsername;
    @BindView(R.id.log_in_password)
    EditText inputPassword;
    @BindView(R.id.loading)
    ImageView loading;
    @BindView(R.id.loading_text)
    TextView loadingText;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loading.setImageDrawable(getLoadingDrawable());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUIState(LOG_IN);
    }

    private Drawable getLoadingDrawable() {
        CircularProgressDrawable drawable = new CircularProgressDrawable(this);
        drawable.setCenterRadius(getResources().getDimension(R.dimen.circle_loading_radius));
        drawable.setStrokeWidth(getResources().getDimension(R.dimen.circle_loading_stroke_width));
        drawable.setColorSchemeColors(Color.WHITE, ContextCompat.getColor(this, R.color.colorPrimary));
        drawable.start();

        return drawable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void signInUnsuccessful() {
        setUIState(LOG_IN);
        Snackbar.make(parent, R.string.log_in_unauthorized_error, Snackbar.LENGTH_LONG).show();
    }

    private void showFieldsIsNotFilledError() {
        setUIState(LOG_IN);
        Snackbar.make(parent, R.string.log_in_field_empty_error, Snackbar.LENGTH_LONG).show();
    }

    private void showServerErrorMsg() {
        setUIState(LOG_IN);
        Snackbar.make(parent, R.string.log_in_server_error, Snackbar.LENGTH_LONG).show();
    }

    private boolean isUserDataValid() {
        return !TextUtils.isEmpty(inputUsername.getText()) && !TextUtils.isEmpty(inputPassword.getText());
    }

    private void setUIState(@UIState int state) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(parent);
        // update views visibility
        switch (state) {
            case LOG_IN:
                constraintSet.setVisibility(R.id.top_logo, ConstraintSet.VISIBLE);
                constraintSet.setVisibility(R.id.log_in_username, ConstraintSet.VISIBLE);
                constraintSet.setVisibility(R.id.log_in_password, ConstraintSet.VISIBLE);
                constraintSet.setVisibility(R.id.log_in_button, ConstraintSet.VISIBLE);
                constraintSet.setVisibility(R.id.loading, ConstraintSet.GONE);
                constraintSet.setVisibility(R.id.loading_text, ConstraintSet.GONE);
                break;
            case LOADING_LOG_IN:
            case LOADING_SERVERS:
                constraintSet.setVisibility(R.id.top_logo, ConstraintSet.GONE);
                constraintSet.setVisibility(R.id.log_in_username, ConstraintSet.GONE);
                constraintSet.setVisibility(R.id.log_in_password, ConstraintSet.GONE);
                constraintSet.setVisibility(R.id.log_in_button, ConstraintSet.GONE);
                constraintSet.setVisibility(R.id.loading, ConstraintSet.VISIBLE);
                constraintSet.setVisibility(R.id.loading_text, ConstraintSet.VISIBLE);
                break;
        }
        Transition transition = new AutoTransition();
        transition.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        TransitionManager.beginDelayedTransition(parent, transition);
        constraintSet.applyTo(parent);
        // update loading text
        switch (state) {
            case LOADING_LOG_IN:
                loadingText.setText(R.string.log_in_logging_in_msg);
                break;
            case LOADING_SERVERS:
                loadingText.setText(R.string.log_in_fetching_servers_msg);
                break;
        }
    }

    @OnClick(R.id.log_in_button)
    public void onLogInClicked() {
        if (!isUserDataValid()) {
            showFieldsIsNotFilledError();
            return;
        }
        // proceed with log in
        setUIState(LOADING_LOG_IN);
        User user = new User(inputUsername.getText().toString(), inputPassword.getText().toString());
        compositeDisposable.add(Api.getApiService()
                .logIn(user)
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(logInResponse -> {
                    if (logInResponse.code() != 200) {
                        signInUnsuccessful();
                    } else {
                        Token response = logInResponse.body();
                        if (response != null) {
                            loadServersList(response.getToken());
                        } else {
                            signInUnsuccessful();
                        }
                    }
                }, throwable -> signInUnsuccessful()));
    }

    private void loadServersList(String token) {
        setUIState(LOADING_SERVERS);
        compositeDisposable.add(Api.getApiService()
                .getServers(getString(R.string.log_in_header_pattern, token))
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listResponse -> {
                    if (listResponse.code() != 200) {
                        showServerErrorMsg();
                    } else {
                        openServerListScreen();
                    }
                }, throwable -> showServerErrorMsg()));
    }

    private void openServerListScreen() {

    }
}
