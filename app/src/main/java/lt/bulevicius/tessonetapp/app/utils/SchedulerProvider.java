package lt.bulevicius.tessonetapp.app.utils;

import io.reactivex.ObservableTransformer;

public interface SchedulerProvider {

    <T> ObservableTransformer<T, T> applySchedulers();
}
