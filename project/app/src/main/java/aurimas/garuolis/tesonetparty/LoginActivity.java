package aurimas.garuolis.tesonetparty;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;

import aurimas.garuolis.tesonetparty.data.DbHelper;
import aurimas.garuolis.tesonetparty.data.PartyContract;
import aurimas.garuolis.tesonetparty.utils.ApiUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = LoginActivity.class.toString();
    private static final String PREF_KEY_USERNAME = "loginUsername";

    private static final int ANIM_BG_MOVE_DURATION      = 400;
    private static final int ANIM_FADEIN_DURATION       = 300;
    private static final int ANIM_FADEOUT_DURATION      = 300;

    private String mUsername;
    private String mPassword;

    private Button mLoginBtn;
    private EditText mEditUser;
    private EditText mEditPass;
    private TextView mLoadingInfo;
    private ProgressBar mProgressBar;
    private ViewGroup mInputWrap;
    private ImageView mLogo;
    private ImageView mBackground;

    ValueAnimator fadeIn;
    ValueAnimator fadeOut;

    private SQLiteDatabase mDb;
    private OkHttpClient client = new OkHttpClient();

    private boolean debug = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        debug = getResources().getBoolean(R.bool.debug);

        mEditUser = (EditText) findViewById(R.id.et_login_username);
        mEditPass = (EditText) findViewById(R.id.et_login_password);


        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading);
        mLoadingInfo = (TextView) findViewById(R.id.tv_loading_info);
        mInputWrap = (ViewGroup) findViewById(R.id.vg_input_wrap);
        mLogo = (ImageView) findViewById(R.id.iv_logo);
        mBackground = (ImageView) findViewById(R.id.iv_background);
        mLoginBtn   = (Button) findViewById(R.id.btn_login);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mUsername = prefs.getString(PREF_KEY_USERNAME, "");

        mEditUser.setText(mUsername);


        // initiate login when "done" clicked on the keyboard
        mEditPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int action = actionId & EditorInfo.IME_MASK_ACTION;
                if (action == EditorInfo.IME_ACTION_DONE) {
                    initiateLogin();
                }
                return false;
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiateLogin();
            }
        });

        mDb = new DbHelper(this).getReadableDatabase();

        showInputViews();
    }

    private void initiateLogin() {
        if (!ApiUtils.HasConnection(this)) {
            Snackbar bar = Snackbar.make(findViewById(R.id.body), getString(R.string.error_no_internet_connect), Snackbar.LENGTH_LONG);
            bar.getView().setBackgroundColor(getResources().getColor(R.color.colorError));
            bar.show();
        } else if (!validateUserInput()) {
            Snackbar bar = Snackbar.make(findViewById(R.id.body), getString(R.string.error_invalid_input_data), Snackbar.LENGTH_LONG);
            bar.getView().setBackgroundColor(getResources().getColor(R.color.colorError));
            bar.show();
        } else {
            showLoadingViews();
            requestAccessToken(mUsername, mPassword);
        }
    }

    // fade out inputs, align background and show loading UI
    private void showLoadingViews(){
        if (fadeIn != null) fadeIn.cancel();
        if (fadeOut != null) fadeOut.cancel();

        mLoginBtn.setEnabled(false);
        mEditPass.setEnabled(false);
        mEditUser.setEnabled(false);

        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mBackground.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(params.bottomMargin, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                params.bottomMargin = (Integer) valueAnimator.getAnimatedValue();
                mBackground.requestLayout();
            }
        });
        animator.setDuration(ANIM_BG_MOVE_DURATION);
        animator.start();

        fadeIn = ValueAnimator.ofFloat(0.0f, 1.0f);
        fadeIn.setDuration(ANIM_FADEIN_DURATION);
        fadeIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                mLoadingInfo.setAlpha(alpha);
                mProgressBar.setAlpha(alpha);
            }
        });

        fadeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mLoadingInfo.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });

        fadeOut = ValueAnimator.ofFloat(1.0f, 0.0f);
        fadeOut.setDuration(ANIM_FADEOUT_DURATION);
        fadeOut.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                mInputWrap.setAlpha(alpha);
                mLogo.setAlpha(alpha);
            }
        });

        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                mInputWrap.setVisibility(View.GONE);
                mLogo.setVisibility(View.GONE);
                fadeIn.start();
            }
        });

        fadeOut.start();
    }


    /// fade in inputs and align background
    private void showInputViews() {
        if (fadeIn != null) fadeIn.cancel();
        if (fadeOut != null) fadeOut.cancel();

        mLoginBtn.setEnabled(true);
        mEditPass.setEnabled(true);
        mEditUser.setEnabled(true);

        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mBackground.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(params.bottomMargin, (int) getResources().getDimension(R.dimen.login_bg_margin_bottom));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                params.bottomMargin = (Integer) valueAnimator.getAnimatedValue();
                mBackground.requestLayout();
            }
        });
        animator.setDuration(ANIM_BG_MOVE_DURATION);
        animator.start();

        mLoadingInfo.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);

        fadeIn = ValueAnimator.ofFloat(0.0f, 1.0f);
        fadeIn.setDuration(ANIM_FADEIN_DURATION);
        fadeIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                mInputWrap.setAlpha(alpha);
                mLogo.setAlpha(alpha);
            }
        });

        fadeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mInputWrap.setAlpha(0.0f);
                mLogo.setAlpha(0.0f);
                mInputWrap.setVisibility(View.VISIBLE);
                mLogo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });

        fadeIn.start();
    }

    private boolean validateUserInput() {
        if (mEditUser.getText().length() == 0 || mEditPass.getText().length() == 0) {
            return false;
        }

        mUsername = mEditUser.getText().toString();
        mPassword = mEditPass.getText().toString();

        SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        prefEditor.putString(PREF_KEY_USERNAME, mUsername);
        prefEditor.commit();

        return true;
    }

    private void requestAccessToken(String user, String pass) {
        updateInfoString(getString(R.string.info_requesting_token));

        JSONObject bodyJson = new JSONObject();
        try {
            bodyJson.put("username", user);
            bodyJson.put("password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

            Request.Builder builder = new Request.Builder().url(ApiUtils.API_URL_TOKEN);
            builder.post(RequestBody.create(ApiUtils.MEDIA_TYPE_JSON, bodyJson.toString()));


            Request request = builder.build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failWithSnack(getString(R.string.error_api_request_failed, e.getLocalizedMessage()));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        threadSleep();
                        parseTokenCallResponse(response.body().string());
                    } else if (response.code() == 401) {
                        failWithSnack(getString(R.string.error_invalid_credentials));
                    } else {
                        failWithSnack( getString(R.string.error_api_request_failed, response.body().string()));
                    }
                }
            });
        }
    }

    private void parseTokenCallResponse(String responseString){
        try {

            JSONObject jo = new JSONObject(responseString);
            if (jo.has("token")) {
                final String jsonToken = jo.getString("token");
                requestServerList(jsonToken);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void requestServerList(String token){
        updateInfoString(getString(R.string.info_fetching_the_list));

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url(ApiUtils.API_URL_SERVERS);
        builder.addHeader("Authorization", "Bearer " + token);
        Request request = builder.build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                failWithSnack(getString(R.string.error_api_request_failed, e.getLocalizedMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    threadSleep();
                    parseServerListResponse(response.body().string());
                } else {
                    failWithSnack(getString(R.string.error_api_request_failed, response.body().string()));
                }
            }
        });
    }

    private void parseServerListResponse(String responseString){
        updateInfoString(getString(R.string.info_parsing_the_list));
        try {

            JSONArray ja = new JSONArray(responseString);
            int count = ja.length();

            if (count > 0) {
                // remove cached data
                mDb.delete(PartyContract.ServerEntry.TABLE_NAME, null, null);

                mDb.beginTransaction();

                for (int i = 0; i < count; i++) {
                    JSONObject jo = ja.getJSONObject(i);

                    ContentValues values = new ContentValues();
                    values.put(PartyContract.ServerEntry.COL_NAME, jo.getString("name"));
                    values.put(PartyContract.ServerEntry.COL_DISTANCE, jo.getString("distance"));
                    mDb.insert(PartyContract.ServerEntry.TABLE_NAME, null, values);
                }
                mDb.setTransactionSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDb.endTransaction();


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showListActivity("");
                }
            });
        }
    }

    // Show error snack and login inputs
    private void failWithSnack(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar bar = Snackbar.make(findViewById(R.id.body), message, Snackbar.LENGTH_LONG);
                bar.getView().setBackgroundColor(getResources().getColor(R.color.colorError));
                bar.show();

                showInputViews();
            }
        });
    }

    private void updateInfoString(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLoadingInfo.setText(message);
            }
        });
    }

    // sleep thread for demonstration purposes
    // otherwise loading screen might not be seen
    private void threadSleep() {
        if (debug) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showListActivity(String token) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

}
