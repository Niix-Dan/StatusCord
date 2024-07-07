package niix.dan.statuscord.Command.Cmds;

import niix.dan.statuscord.Command.CmdBase;
import niix.dan.statuscord.Monitor.Cpu.CpuInfo;
import niix.dan.statuscord.Monitor.Disk.DiskUsage;
import niix.dan.statuscord.Monitor.Memory.GarbageCollectionMonitor;
import niix.dan.statuscord.Monitor.Memory.GarbageCollectorStatistics;
import niix.dan.statuscord.Monitor.Memory.MemoryInfo;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class Teste extends CmdBase {
    public Teste() {
        forcePlayer = false;
        cmdName = "status";
    }
    @Override
    public boolean run() {

        sendInfo("CpuModel", CpuInfo.queryCpuModel());
        sendInfo("DiskUsage", DiskUsage.getUsed()+"/"+DiskUsage.getTotal());
        sendInfo("PhysicalMemory", MemoryInfo.getAvailablePhysicalMemory()+"/"+MemoryInfo.getTotalPhysicalMemory()+ " (" +MemoryInfo.getAvailablePhysicalMemory() + " free)");
        sendInfo("TotalVirtualMemory", MemoryInfo.getTotalVirtualMemory());
        sendInfo("Swap", MemoryInfo.getUsedSwap()+"/"+MemoryInfo.getTotalSwap() + " ("+MemoryInfo.getAvailableSwap() + " free)");

        return true;
    }

    private void sendInfo(String entry, Object info) {
        sender.sendMessage(ChatColor.RED + entry + ChatColor.GREEN + " > " + ChatColor.GOLD + info);
    }

}
