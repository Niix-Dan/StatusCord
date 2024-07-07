package niix.dan.statuscord.Utils;

import java.io.File;

public class memUtils {
    public long maxusedmemory = 0;


    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / 1024 / 1024;
    }
    public long getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / 1024 / 1024;
    }
    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory() / 1024 / 1024;
    }
    public long getUsedMemory() {
        if(getTotalMemory() - getFreeMemory() >= maxusedmemory) maxusedmemory = getTotalMemory() - getFreeMemory();

        return getTotalMemory() - getFreeMemory();
    }
    public long getTotalUsedMemory() {
        return maxusedmemory;
    }

    public long getMaxUsedMemory() {
        return maxusedmemory;
    }

    public long getTotalDiskSpace() {
        File root = new File(".");
        return root.getTotalSpace() / 1024 / 1024 / 1024; // Convert bytes to gigabytes
    }

    public long getFreeDiskSpace() {
        File root = new File(".");
        return root.getFreeSpace() / 1024 / 1024 / 1024; // Convert bytes to gigabytes
    }
}
