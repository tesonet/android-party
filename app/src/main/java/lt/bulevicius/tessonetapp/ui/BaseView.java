package lt.bulevicius.tessonetapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

import io.reactivex.disposables.CompositeDisposable;

/**
 * The type Base view.
 */
public abstract class BaseView extends Controller {

    protected final CompositeDisposable subscriptions = new CompositeDisposable();

    @NonNull
    @Override
    protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View contentView = getContentView(inflater, container);
        doBindViews(contentView);
        return contentView;
    }

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        super.onContextAvailable(context);
        doInject();
    }


    @Override
    protected void onDetach(@NonNull View view) {
        subscriptions.clear();
        super.onDetach(view);
    }

    /**
     * Gets content view.
     *
     * @param inflater  the inflater
     * @param container the container
     * @return the content view
     */
    public abstract View getContentView(LayoutInflater inflater, ViewGroup container);

    /**
     * This is where you should do dagger injection.
     */
    public abstract void doInject();

    /**
     * Do bind views with butter knife.
     *
     * @param view the view
     */
    public abstract void doBindViews(View view);
}
