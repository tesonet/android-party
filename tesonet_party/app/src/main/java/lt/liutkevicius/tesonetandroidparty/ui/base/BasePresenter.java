package lt.liutkevicius.tesonetandroidparty.ui.base;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T> implements Presenter<T> {

    protected final CompositeDisposable subscriptions = new CompositeDisposable();
    private T view;

    public boolean hasView() {
        return view != null;
    }

    public T getView() {
        return view;
    }

    @Override
    public void setView(T view) {
        this.view = view;
        if (view == null) {
            subscriptions.clear();
        }
    }
}
