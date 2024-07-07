package niix.dan.statuscord.Monitor.Tick;

/**
 * A reporting callback for the game's "tick loop".
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/tick/TickReporter.java#L26
 */
public interface TickReporter extends AutoCloseable {

    /**
     * Starts the reporter
     */
    void start();

    /**
     * Stops the reporter
     */
    @Override
    void close();

    /**
     * Adds a callback to be called each time the tick increments
     *
     * @param runnable the callback
     */
    void addCallback(Callback runnable);

    /**
     * Removes a callback
     *
     * @param runnable callback
     */
    void removeCallback(Callback runnable);

    interface Callback {
        void onTick(double duration);
    }

}
