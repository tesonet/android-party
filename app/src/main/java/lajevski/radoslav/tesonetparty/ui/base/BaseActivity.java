package lajevski.radoslav.tesonetparty.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lajevski.radoslav.tesonetparty.App;
import lajevski.radoslav.tesonetparty.R;
import lajevski.radoslav.tesonetparty.di.ActivityComponent;
import lajevski.radoslav.tesonetparty.di.ActivityModule;
import lajevski.radoslav.tesonetparty.di.DaggerActivityComponent;
import lajevski.radoslav.tesonetparty.utils.FragmentUtils;

/**
 * Created by Radoslav on 1/16/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {


    Unbinder mUnbinder;

    /**
     * The current fragment.
     */
    private Fragment mCurrentFragment = null;

    /**
     * The action bar.
     */
    protected Toolbar mToolbar;


    /**
     * Activity component
     */
    // private ActivityComponent mActivityComponent;

    /**
     * @return fragment for to be used in fragment transaction
     */
    protected abstract Fragment getFragment();

    /**
     * @return fragment tag, used to identify fragment
     */
    protected abstract String getFragmentTag();

    /**
     * Activity component
     */
    private ActivityComponent mActivityComponent;

    /**
     * Gets the content view.
     *
     * @return the content view
     */

    protected abstract Integer getContentView();

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUnbinder = ButterKnife.bind(this);

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();

        // Set the content view from the concrete activity.
        // Do not do anything if the value is null, because that means the activity handles its own content view.
        if (getContentView() != null) {
            setContentView(getContentView());
        }

        requestActionBar();

        switchContent(getFragment(), getFragmentTag());
    }

    /**
     * Creates the action bar.
     *
     * @return the action bar
     */
    public Toolbar createActionBar() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        mToolbar.setLogo(R.mipmap.action_bar_icon);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        return mToolbar;
    }

    /**
     * Request action bar.
     *
     * @return the action bar
     */
    public Toolbar requestActionBar() {
        if (mToolbar == null)
            return createActionBar();
        else
            return mToolbar;
    }


    /**
     * Switch content with provided fragment.
     *
     * @param fragment    the fragment
     * @param fragmentTag tag, that identifies fragment in back stack trace
     */
    public void switchContent(Fragment fragment, String fragmentTag) {

        if (fragment != null) {

            setCurrentFragment(fragment);

            FragmentManager manager = getSupportFragmentManager();

            // If this fragment is in back stack trace remove it
            manager.popBackStackImmediate(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            manager.beginTransaction()
                    .replace(R.id.content, fragment, fragmentTag)
                    .addToBackStack(fragmentTag)
                    .commit();
        }
    }

    /**
     * Gets an Activity component
     *
     * @return Activity component
     */
    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    public void setCurrentFragment(Fragment mCurrentFragment) {
        this.mCurrentFragment = mCurrentFragment;
    }

    @Override
    public void onBackPressed() {

        // If this is not a home screen, then pop the bach stack
        if (getSupportFragmentManager().getBackStackEntryCount() >= 2) {

            getSupportFragmentManager().popBackStack();

        } else {
            FragmentUtils.BackStackDoubleTapExit(this);
        }
    }

}
