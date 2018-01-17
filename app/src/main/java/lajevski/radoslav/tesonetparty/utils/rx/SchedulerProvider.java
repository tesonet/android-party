package lajevski.radoslav.tesonetparty.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Radoslav on 1/16/2018.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
