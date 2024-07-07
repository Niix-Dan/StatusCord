package niix.dan.statuscord.Command.Cmds;

import niix.dan.statuscord.Command.CmdBase;
import niix.dan.statuscord.Monitor.Cpu.CpuInfo;
import niix.dan.statuscord.Monitor.Cpu.CpuMonitor;
import niix.dan.statuscord.Monitor.Disk.DiskUsage;
import niix.dan.statuscord.Monitor.Memory.MemoryInfo;
import org.bukkit.ChatColor;

public class info extends CmdBase {
    public info() {
        forcePlayer = false;
        cmdName = "info";
        permission = "statuscord.infos";
    }
    @Override
    public boolean run() {
        sendInfo("CpuModel", CpuInfo.queryCpuModel());
        sendInfo("CpuLoad", String.format("%.2f", CpuMonitor.processLoad() * 100).replace(",", ".") + "% (" + String.format("%.1f", CpuMonitor.systemLoad10SecAvg() * 100).replace(",", ".") +"% 10s Avg)");
        sender.sendMessage(" ");
        sendInfo("DiskUsage", toGb(DiskUsage.getUsed())+"gb/"+toGb(DiskUsage.getTotal())+"gb");
        sendInfo("PhysicalMemory", toMb(MemoryInfo.getUsedPhysicalMemory())+"mb/"+toMb(MemoryInfo.getTotalPhysicalMemory())+ "mb (" +toMb(MemoryInfo.getAvailablePhysicalMemory()) + "mb free)");
        sendInfo("Swap", toMb(MemoryInfo.getUsedSwap())+"mb/"+toMb(MemoryInfo.getTotalSwap()) + "mb ("+toMb(MemoryInfo.getAvailableSwap()) + "mb free)");
        sendInfo("TotalVirtualMemory", toMb(MemoryInfo.getTotalVirtualMemory())+"mb");

        return false;
    }

    private void sendInfo(String entry, Object info) {
        sender.sendMessage(ChatColor.RED + entry + ChatColor.GREEN + " > " + ChatColor.GOLD + info);
    }

    private String toGb(float btdata) {
        return String.format("%.2f", btdata / 1024 / 1024 / 1024).replace(",", ".");
    }
    private String toMb(float btdata) {
        return String.format("%.2f", btdata / 1024 / 1024).replace(",", ".");
    }

}
