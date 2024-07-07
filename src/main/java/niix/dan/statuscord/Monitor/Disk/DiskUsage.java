package niix.dan.statuscord.Monitor.Disk;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Exposes the system disk usage.
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/monitor/disk/DiskUsage.java
 */
public enum DiskUsage {
    ;

    private static final FileStore FILE_STORE;

    static {
        FileStore fileStore = null;
        try {
            fileStore = Files.getFileStore(Paths.get("."));
        } catch (IOException e) {
            // ignore
        }
        FILE_STORE = fileStore;
    }

    public static float getUsed() {
        if (FILE_STORE == null) {
            return 0;
        }

        try {
            float total = FILE_STORE.getTotalSpace();
            return total - FILE_STORE.getUsableSpace();
        } catch (IOException e) {
            return 0;
        }
    }

    public static float getTotal() {
        if (FILE_STORE == null) {
            return 0;
        }

        try {
            return FILE_STORE.getTotalSpace();
        } catch (IOException e) {
            return 0;
        }
    }

}