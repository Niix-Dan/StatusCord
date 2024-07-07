package niix.dan.statuscord.Monitor.Tick;

import niix.dan.statuscord.Utils.DoubleAverageInfo;

public interface TickStatistics {

    double tps5Sec();
    double tps10Sec();
    double tps1Min();
    double tps5Min();
    double tps15Min();

    boolean isDurationSupported();

    DoubleAverageInfo duration10Sec();
    DoubleAverageInfo duration1Min();
    DoubleAverageInfo duration5Min();

}
