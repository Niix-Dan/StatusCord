package niix.dan.statuscord.Monitor.Ping;

import java.util.Arrays;

/**
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/monitor/ping/PingSummary.java
 */
public final class PingSummary {

    private final int[] values;
    private final int total;
    private final int max;
    private final int min;
    private final double mean;

    public PingSummary(int[] values) {
        Arrays.sort(values);
        this.values = values;

        int total = 0;
        for (int value : values) {
            total += value;
        }
        this.total = total;

        this.mean = (double) total / values.length;
        this.max = values[values.length - 1];
        this.min = values[0];
    }

    public int total() {
        return this.total;
    }

    public double mean() {
        return this.mean;
    }

    public int max() {
        return this.max;
    }

    public int min() {
        return this.min;
    }

    public int percentile(double percentile) {
        if (percentile < 0 || percentile > 1) {
            throw new IllegalArgumentException("Invalid percentile " + percentile);
        }

        int rank = (int) Math.ceil(percentile * (this.values.length - 1));
        return this.values[rank];
    }

    public double median() {
        return percentile(0.50d);
    }

    public double percentile95th() {
        return percentile(0.95d);
    }

}