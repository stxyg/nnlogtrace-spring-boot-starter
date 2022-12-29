package io.github.stxyg.nnlogtrace.multithread;

/**
 * @author stxyg
 * @since 2022/11/12
 **/
public class DefaultLogTraceRunnable extends AbstractLogTraceRunnable {

    private final Runnable runnable;

    public DefaultLogTraceRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void logTraceRun() {
        this.runnable.run();
    }

}
