package me.lucko.spark.common;

import com.google.common.base.Preconditions;
import com.sk89q.warmroast.Sampler;
import com.sk89q.warmroast.ThreadDumper;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Builds {@link Sampler} instances.
 */
public class SamplerBuilder {

    private int samplingInterval = 10;
    private long timeout = -1;
    private ThreadDumper threadDumper = new ThreadDumper.All();

    public SamplerBuilder() {
    }

    public SamplerBuilder samplingInterval(int samplingInterval) {
        this.samplingInterval = samplingInterval;
        return this;
    }

    public SamplerBuilder completeAfter(long timeout, TimeUnit unit) {
        Preconditions.checkArgument(timeout > 0, "time > 0");
        this.timeout = System.currentTimeMillis() + unit.toMillis(timeout);
        return this;
    }

    public SamplerBuilder threadDumper(ThreadDumper threadDumper) {
        this.threadDumper = threadDumper;
        return this;
    }

    public Sampler start(Timer samplingThread, ExecutorService workerPool) {
        Sampler sampler = new Sampler(this.samplingInterval, this.threadDumper, this.timeout);
        sampler.start(samplingThread, workerPool);
        return sampler;
    }

}
