package com.svyd.tesonet.presentation.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 * Base class for activities that handles initialization of MVP components.
 * Use {@link #getPresenter()} to access the presenter which belongs to this view.
 *
 * @param <F> a concrete type of {@link PresenterProvider} which can handle a creation of
 *           particular {@link BasePresenter} implementation,
 *           which is a second type argument (P) of this class
 *
 * @param <P> {@link BasePresenter} implementation that is needed for this view.
 */

@SuppressWarnings("unchecked")
public abstract class BaseActivity<F extends PresenterProvider, P extends BasePresenter> extends AppCompatActivity {

    private BasePresenter mPresenter;

    //region Activity lifecycle methods

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());
        initializePresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    //endregion

    //region Presenter initialization

    /**
     * Create {@link BasePresenter} using {@link PresenterProvider}
     * and initialize it.
     */

    private void initializePresenter() {
        mPresenter = createPresenterProvider()
                .providePresenter(getIntent().getExtras(), getViewClass());

        mPresenter.initialize();
    }

    /**
     * @return new instance of particular {@link PresenterProvider}
     */

    protected abstract F createPresenterProvider();

    /**
     * Used by {@link PresenterProvider#providePresenter(Bundle, BaseView)} as argument.
     *
     * @return an instance of this class casted to {@link BaseView}
     */

    private BaseView getViewClass() {
        if (this instanceof BaseView) {
            return ((BaseView) this);
        } else {
            return null;
        }
    }

    //endregion

    /**
     * A subclass of this {@link BaseActivity} has to provide a layout resource ID
     * for Content View. Used for {@link AppCompatActivity#setContentView(int)}
     *
     * @return ID of {@link AppCompatActivity}'s layout resource.
     */

    @LayoutRes
    protected abstract int getContentView();

    /**
     * @return {@link BasePresenter} for this {@link BaseView}
     */

    protected P getPresenter() {
        return (P) mPresenter;
    }

}
