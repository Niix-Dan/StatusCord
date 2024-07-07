package niix.dan.statuscord.Utils;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Utility to find active threads.
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/util/ThreadFinder.java
 */
public final class ThreadFinder {

    private static final ThreadGroup ROOT_THREAD_GROUP;
    static {
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentGroup;
        while ((parentGroup = rootGroup.getParent()) != null) {
            rootGroup = parentGroup;
        }
        ROOT_THREAD_GROUP = rootGroup;
    }

    // cache the approx active count at the time of construction.
    // the usages of this class are likely to be somewhat short-lived, so it's good
    // enough to just cache a value on init.
    private int approxActiveCount = ROOT_THREAD_GROUP.activeCount();

    /**
     * Gets a stream of all known active threads.
     *
     * @return a stream of threads
     */
    public Stream<Thread> getThreads() {
        Thread[] threads = new Thread[this.approxActiveCount + 10]; // +10 to allow a bit of growth for newly created threads
        int len;
        while ((len = ROOT_THREAD_GROUP.enumerate(threads, true)) == threads.length) {
            threads = new Thread[threads.length * 2];
        }
        this.approxActiveCount = len;
        return Arrays.stream(threads, 0, len);
    }

}
