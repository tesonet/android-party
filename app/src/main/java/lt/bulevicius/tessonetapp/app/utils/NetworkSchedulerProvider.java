package lt.bulevicius.tessonetapp.app.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

public class NetworkSchedulerProvider implements SchedulerProvider {

    private final Scheduler subscribeOn;
    private final Scheduler observeOn;

    public NetworkSchedulerProvider(Scheduler subscribeOn, Scheduler observeOn) {
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
    }

    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return upstream -> upstream.subscribeOn(subscribeOn).observeOn(observeOn);
    }
}
