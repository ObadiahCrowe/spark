package me.lucko.spark.skyfall;

import io.skyfallsdk.Server;
import me.lucko.spark.common.sampler.tick.AbstractTickHook;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SkyfallTickHook extends AbstractTickHook implements Runnable {

    private ScheduledFuture<?> future;

    @Override
    public void run() {
        this.onTick();
    }

    @Override
    public void start() {
        this.future = Server.get().getScheduler().scheduleAtFixedRate(this, 0L, 50L, TimeUnit.MILLISECONDS);
    }

    @Override
    public void close() {
        this.future.cancel(true);
    }
}
