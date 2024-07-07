package niix.dan.statuscord.Utils;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/util/SparkThreadFactory.java
 */
public class ScordThreadFactory implements ThreadFactory {

    public static final Thread.UncaughtExceptionHandler EXCEPTION_HANDLER = (t, e) -> {
        System.err.println("Uncaught exception thrown by thread " + t.getName());
        e.printStackTrace();
    };

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public ScordThreadFactory() {
        this.namePrefix = "statuscord-worker-pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, this.namePrefix + this.threadNumber.getAndIncrement());
        t.setUncaughtExceptionHandler(EXCEPTION_HANDLER);
        t.setDaemon(true);
        return t;
    }

}