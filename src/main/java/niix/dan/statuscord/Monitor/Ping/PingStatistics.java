package niix.dan.statuscord.Monitor.Ping;

import niix.dan.statuscord.Monitor.MonitoringExecutor;
import niix.dan.statuscord.Utils.RollingAverage;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Provides statistics for player ping RTT to the server.
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/monitor/ping/PingStatistics.java
 */
public final class PingStatistics implements Runnable, AutoCloseable {
    private static final int QUERY_RATE_SECONDS = 10;
    private static final int WINDOW_SIZE_SECONDS = (int) TimeUnit.MINUTES.toSeconds(15); // 900
    private static final int WINDOW_SIZE = WINDOW_SIZE_SECONDS / QUERY_RATE_SECONDS; // 90

    /** The platform function that provides player ping times */
    private final PlayerPingProvider provider;
    /** Rolling average of the median ping across all players */
    private final RollingAverage rollingAverage = new RollingAverage(WINDOW_SIZE);

    /** The scheduler task that polls pings and calculates the rolling average */
    private ScheduledFuture<?> future;

    public PingStatistics(PlayerPingProvider provider) {
        this.provider = provider;
    }

    /**
     * Starts the statistics monitor
     */
    public void start() {
        if (this.future != null) {
            throw new IllegalStateException();
        }
        this.future = MonitoringExecutor.INSTANCE.scheduleAtFixedRate(this, QUERY_RATE_SECONDS, QUERY_RATE_SECONDS, TimeUnit.SECONDS);
    }

    @Override
    public void close() {
        if (this.future != null) {
            this.future.cancel(false);
            this.future = null;
        }
    }

    @Override
    public void run() {
        PingSummary summary = currentSummary();
        if (summary.total() == 0) {
            return;
        }

        this.rollingAverage.add(BigDecimal.valueOf(summary.median()));
    }

    /**
     * Gets the ping rolling average.
     *
     * @return the rolling average
     */
    public RollingAverage getPingAverage() {
        return this.rollingAverage;
    }

    /**
     * Queries a summary of current player pings.
     *
     * @return a summary of current pings
     */
    public PingSummary currentSummary() {
        Map<String, Integer> results = this.provider.poll();
        int[] values = results.values().stream().filter(ping -> ping > 0).mapToInt(i -> i).toArray();
        return values.length == 0
                ? new PingSummary(new int[]{0})
                : new PingSummary(values);
    }

    /**
     * Queries the ping of a given player.
     *
     * @param playerName the name of the player
     * @return the ping, if available
     */
    public @Nullable PlayerPing query(String playerName) {
        Map<String, Integer> results = this.provider.poll();

        // try exact match
        Integer result = results.get(playerName);
        if (result != null) {
            return new PlayerPing(playerName, result);
        }

        // try case-insensitive match
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(playerName)) {
                return new PlayerPing(
                        entry.getKey(),
                        entry.getValue()
                );
            }
        }

        return null;
    }

    public static final class PlayerPing {
        private final String name;
        private final int ping;

        PlayerPing(String name, int ping) {
            this.name = name;
            this.ping = ping;
        }

        public String name() {
            return this.name;
        }

        public int ping() {
            return this.ping;
        }
    }

}
