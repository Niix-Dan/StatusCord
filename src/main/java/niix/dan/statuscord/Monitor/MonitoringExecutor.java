package niix.dan.statuscord.Monitor;

import niix.dan.statuscord.Utils.ScordThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/monitor/MonitoringExecutor.java
 */
public enum MonitoringExecutor {
    ;

    /** The executor used to monitor & calculate rolling averages. */
    public static final ScheduledExecutorService INSTANCE = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread thread = Executors.defaultThreadFactory().newThread(r);
        thread.setName("spark-monitoring-thread");
        thread.setUncaughtExceptionHandler(ScordThreadFactory.EXCEPTION_HANDLER);
        thread.setDaemon(true);
        return thread;
    });
}
