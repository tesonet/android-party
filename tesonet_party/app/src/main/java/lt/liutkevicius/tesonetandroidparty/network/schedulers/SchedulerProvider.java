package lt.liutkevicius.tesonetandroidparty.network.schedulers;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

public class SchedulerProvider implements ISchedulerProvider {

    private final Scheduler subscribeOn;
    private final Scheduler observeOn;

    public SchedulerProvider(Scheduler subscribeOn, Scheduler observeOn) {
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
    }

    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return upstream -> upstream.subscribeOn(subscribeOn).observeOn(observeOn);
    }
}
