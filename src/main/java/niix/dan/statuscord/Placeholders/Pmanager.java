package niix.dan.statuscord.Placeholders;

import niix.dan.statuscord.Monitor.Cpu.CpuInfo;
import niix.dan.statuscord.Monitor.Cpu.CpuMonitor;
import niix.dan.statuscord.Monitor.Disk.DiskUsage;
import niix.dan.statuscord.Monitor.Memory.MemoryInfo;
import org.bukkit.Bukkit;

import static niix.dan.statuscord.StatusCord.*;

public class Pmanager implements Placeholder {
    private final PapiExpansion papiExpansion;

    private String name;

    public Pmanager(PapiExpansion papiExpansion, String name) {
        this.papiExpansion = papiExpansion;
        this.name = name;
    }

    @Override
    public String process(String params) {

        switch(this.name) {
            // Cpu model
            case "cpumodel":
                return CpuInfo.queryCpuModel();

            // Process Load
            case "procload":
                return String.format("%.2f", CpuMonitor.processLoad() * 100).replace(",", ".");
            case "procload_10s":
                return String.format("%.2f", CpuMonitor.processLoad10SecAvg() * 100).replace(",", ".");
            case "procload_1m":
                return String.format("%.2f", CpuMonitor.processLoad1MinAvg() * 100).replace(",", ".");
            case "procload_15m":
                return String.format("%.2f", CpuMonitor.processLoad15MinAvg() * 100).replace(",", ".");

            // System Load
            case "sysload":
                return String.format("%.2f", CpuMonitor.systemLoad() * 100).replace(",", ".");
            case "sysload_10s":
                return String.format("%.2f", CpuMonitor.systemLoad10SecAvg() * 100).replace(",", ".");
            case "sysload_1m":
                return String.format("%.2f", CpuMonitor.systemLoad1MinAvg() * 100).replace(",", ".");
            case "sysload_15m":
                return String.format("%.2f", CpuMonitor.systemLoad15MinAvg() * 100).replace(",", ".");

            // Disk Usage
            case "disk_used":
                return toGb(DiskUsage.getUsed());
            case "disk_total":
                return toGb(DiskUsage.getTotal());
            case "disk_free":
                return toGb(DiskUsage.getTotal() - DiskUsage.getUsed());

            // Physical Memory
            case "physical_used":
                return toMb(MemoryInfo.getUsedPhysicalMemory());
            case "physical_total":
                return toMb(MemoryInfo.getTotalPhysicalMemory());
            case "physical_free":
                return toMb(MemoryInfo.getAvailablePhysicalMemory());

            // Swap Memory
            case "swap_used":
                return toMb(MemoryInfo.getUsedSwap());
            case "swap_total":
                return toMb(MemoryInfo.getTotalSwap());
            case "swap_free":
                return toMb(MemoryInfo.getAvailableSwap());

            // Virtual Memory
            case "virtual_total":
                return toMb(MemoryInfo.getTotalVirtualMemory());

            // Server
            case "tps_5s":
                return ""+tickStatistics.tps5Sec();
            case "tps_10s":
                return ""+tickStatistics.tps10Sec();
            case "tps_1m":
                return ""+tickStatistics.tps1Min();
            case "tps_5m":
                return ""+tickStatistics.tps5Min();
            case "tps_15m":
                return ""+tickStatistics.tps15Min();

            case "online":
                return ""+ Bukkit.getOnlinePlayers().size();
            case "offline":
                return ""+Bukkit.getOfflinePlayers().length;
            case "max":
                return ""+Bukkit.getServer().getMaxPlayers();

            case "ping":
                return ""+pingStatistics.getPingAverage();

            default:
                return "Invalid Placeholder";
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    private String toGb(float btdata) {
        return String.format("%.2f", btdata / 1024 / 1024 / 1024).replace(",", ".");
    }
    private String toMb(float btdata) {
        return String.format("%.2f", btdata / 1024 / 1024).replace(",", ".");
    }
}