package niix.dan.statuscord.Monitor;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Utility for reading from /proc/ on Linux systems.
 * https://github.com/lucko/spark/blob/master/spark-common/src/main/java/me/lucko/spark/common/monitor/LinuxProc.java
 */
public enum LinuxProc {

    /**
     * Information about the system CPU.
     */
    CPUINFO("/proc/cpuinfo"),

    /**
     * Information about the system memory.
     */
    MEMINFO("/proc/meminfo"),

    /**
     * Information about the system network usage.
     */
    NET_DEV("/proc/net/dev"),

    /**
     * Information about the operating system distro.
     */
    OSINFO("/etc/os-release");

    private final Path path;

    LinuxProc(String path) {
        this.path = resolvePath(path);
    }

    private static @Nullable Path resolvePath(String path) {
        try {
            Path p = Paths.get(path);
            if (Files.isReadable(p)) {
                return p;
            }
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    public @NonNull List<String> read() {
        if (this.path != null) {
            try {
                return Files.readAllLines(this.path, StandardCharsets.UTF_8);
            } catch (IOException e) {
                // ignore
            }
        }

        return Collections.emptyList();
    }

}