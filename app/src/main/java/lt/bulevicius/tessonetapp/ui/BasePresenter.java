package lt.bulevicius.tessonetapp.ui;

import io.reactivex.disposables.CompositeDisposable;

/**
 * The type Base presenter.
 *
 * @param <T> the type parameter witch is the view
 */
public abstract class BasePresenter<T> {

    private T view;
    /**
     * The Subscriptions.
     */
    protected final CompositeDisposable subscriptions = new CompositeDisposable();

    /**
     * Has view boolean.
     *
     * @return the boolean
     */
    protected final boolean hasView() {
        return view != null;
    }

    /**
     * Sets view.
     * if view is null, clears all disposables.
     * @param view the view
     */
    public final void setView(T view) {
        this.view = view;
        if(view == null)
            subscriptions.clear();
    }

    /**
     * Get view t.
     *
     * @return the t
     */
    protected final T getView(){
        return view;
    }
}
