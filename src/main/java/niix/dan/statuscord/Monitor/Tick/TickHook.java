package niix.dan.statuscord.Monitor.Tick;

/**
 * A hook with the game's "tick loop".
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/tick/TickHook.java#L26
 */
public interface TickHook extends AutoCloseable {

    /**
     * Starts the hook
     */
    void start();

    /**
     * Stops the hook
     */
    @Override
    void close();

    /**
     * Gets the current tick number
     *
     * @return the current tick
     */
    int getCurrentTick();

    /**
     * Adds a callback to be called each time the tick increments
     *
     * @param runnable the task
     */
    void addCallback(Callback runnable);

    /**
     * Removes a callback
     *
     * @param runnable the callback
     */
    void removeCallback(Callback runnable);

    interface Callback {
        void onTick(int currentTick);
    }

}