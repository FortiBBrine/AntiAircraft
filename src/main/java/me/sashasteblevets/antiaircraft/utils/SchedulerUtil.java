package me.sashasteblevets.antiaircraft.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SchedulerUtil {

    private ScheduledExecutorService executor;

    public SchedulerUtil(int threads) {
        this.executor = Executors.newScheduledThreadPool(threads);
    }

    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return this.executor.schedule(command, delay, unit);
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long delay, long period, TimeUnit unit) {
        return this.executor.scheduleAtFixedRate(command, delay, period, unit);
    }

    public void shutdown() {
        this.executor.shutdown();
    }

}
