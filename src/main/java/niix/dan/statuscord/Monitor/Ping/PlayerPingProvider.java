package niix.dan.statuscord.Monitor.Ping;

import java.util.Map;

/**
 * Provides information about player ping RTT.
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/monitor/ping/PlayerPingProvider.java
 */
@FunctionalInterface
public interface PlayerPingProvider {

    /**
     * Poll current player pings in milliseconds.
     *
     * <p>The map keys are player names and the values are the ping values.</p>
     *
     * @return a map of player pings
     */
    Map<String, Integer> poll();

}
