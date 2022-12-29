package io.github.stxyg.nnlogtrace.multithread;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

/**
 * @author stxyg
 * @since 2022/11/12
 **/
public class LogTraceThreadFactory extends CustomizableThreadFactory {

    private static final long serialVersionUID = -164153466034772787L;

    public LogTraceThreadFactory() {}

    public LogTraceThreadFactory(String threadNamePrefix) {
        super(threadNamePrefix);
    }

    @Override
    public Thread newThread(Runnable r) {
        DefaultLogTraceRunnable logTraceRunnable = new DefaultLogTraceRunnable(r);
        return super.newThread(logTraceRunnable);
    }

}
