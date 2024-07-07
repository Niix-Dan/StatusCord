package niix.dan.statuscord.Utils;

/**
 * Statistics for a recorded double value.
 * https://github.com/lucko/spark/blob/master/spark-api/src/main/java/me/lucko/spark/api/statistic/misc/DoubleAverageInfo.java
 */
public interface DoubleAverageInfo {

    /**
     * Gets the mean value.
     *
     * @return the mean
     */
    double mean();

    /**
     * Gets the max value.
     *
     * @return the max
     */
    double max();

    /**
     * Gets the min value.
     *
     * @return the min
     */
    double min();

    /**
     * Gets the median value.
     *
     * @return the median
     */
    default double median() {
        return percentile(0.50d);
    }

    /**
     * Gets the 95th percentile value.
     *
     * @return the 95th percentile
     */
    default double percentile95th() {
        return percentile(0.95d);
    }

    /**
     * Gets the average value at a given percentile.
     *
     * @param percentile the percentile, as a double between 0 and 1.
     * @return the average value at the given percentile
     */
    double percentile(double percentile);

}