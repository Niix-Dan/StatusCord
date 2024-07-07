package niix.dan.statuscord.Utils;

import niix.dan.statuscord.Monitor.Cpu.CpuInfo;
import niix.dan.statuscord.Monitor.Cpu.CpuMonitor;
import niix.dan.statuscord.Monitor.Disk.DiskUsage;
import niix.dan.statuscord.Monitor.Memory.MemoryInfo;
import org.bukkit.Bukkit;

import static niix.dan.statuscord.StatusCord.*;

public enum ConfigEnum {

    // System
    CpuModel("CpuModel", "cpumodel"),
    ProcessLoad_1("ProcessLoad", "procload"),
    ProcessLoad_2("ProcessLoad_10s", "procload_10s"),
    ProcessLoad_3("ProcessLoad_1m", "procload_1m"),
    ProcessLoad_4("ProcessLoad_15m", "procload_15m"),
    SystemLoad_1("SystemLoad", "sysload"),
    SystemLoad_2("SystemLoad_10s", "sysload_10s"),
    SystemLoad_3("SystemLoad_1m", "sysload_1m"),
    SystemLoad_4("SystemLoad_15m", "sysload_15m"),
    DiskUsed("DiskUsed", "disk_used"),
    DiskTotal("DiskTotal", "disk_total"),
    DiskFree("DiskFree", "disk_free"),
    UsedPhysical("UsedPhysical", "physical_used"),
    TotalPhysical("TotalPhysical", "physical_total"),
    FreePhysical("FreePhysical", "physical_free"),
    UsedSwap("UsedSwap", "swap_used"),
    TotalSwap("TotalSwap", "swap_total"),
    FreeSwap("FreeSwap", "swap_free"),
    VirtualMemory("VirtualMemory", "virtual_total"),

    // Server
    ServerTps_1("ServerTps_5s", "servertps_5s"),
    ServerTps_2("ServerTps_10s", "servertps_10s"),
    ServerTps_3("ServerTps_1m", "servertps_1m"),
    ServerTps_4("ServerTps_5m", "servertps_5m"),
    ServerTps_5("ServerTps_15m", "servertps_15m"),
    OnlinePlayers("OnlinePlayers", "onlineplayers"),
    OfflinePlayers("OfflinePlayers", "offlineplayers"),
    ServerLimit("ServerLimit", "serverlimit"),
    ServerPing("ServerPing", "ping"),
    Uptime("Uptime", "uptime");


    public String hook;
    public String result;

    ConfigEnum(String hook, String result) {
        this.hook = hook;
        this.result = result;
    }

    public String getHook() {
        return this.hook;
    }

    public String getResult() {
        switch(this.result) {
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
            case "servertps_5s":
                return ""+tickStatistics.tps5Sec();
            case "servertps_10s":
                return ""+tickStatistics.tps10Sec();
            case "servertps_1m":
                return ""+tickStatistics.tps1Min();
            case "servertps_5m":
                return ""+tickStatistics.tps5Min();
            case "servertps_15m":
                return ""+tickStatistics.tps15Min();

            case "onlineplayers":
                return ""+Bukkit.getOnlinePlayers().size();
            case "offlineplayers":
                return ""+Bukkit.getOfflinePlayers().length;
            case "serverlimit":
                return ""+Bukkit.getServer().getMaxPlayers();

            case "ping":
                return ""+pingStatistics.getPingAverage();

            case "uptime":
                return "<t:"+serverUptime / 1000+":R>";

            default:
                return "Invalid Placeholder";
        }
    }

    public static ConfigEnum parse(String hook) {
        for (ConfigEnum config : ConfigEnum.values()) {
            if (config.hook.equalsIgnoreCase(hook)) {
                return config;
            }
        }
        return null;
    }

    private static String toGb(float btdata) {
        return String.format("%.2f", btdata / 1024 / 1024 / 1024).replace(",", ".");
    }
    private static String toMb(float btdata) {
        return String.format("%.2f", btdata / 1024 / 1024).replace(",", ".");
    }

}
