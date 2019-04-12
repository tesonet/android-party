package lt.liutkevicius.tesonetandroidparty.network.schedulers;


import io.reactivex.ObservableTransformer;

public interface ISchedulerProvider {

    <T> ObservableTransformer<T, T> applySchedulers();

}