package niix.dan.statuscord.Command.Cmds;


import niix.dan.statuscord.Command.CmdBase;
import org.bukkit.ChatColor;

public class reload extends CmdBase {
    public reload() {
        forcePlayer = false;
        cmdName = "reload";
        permission = "statuscord.reload";
    }

    @Override
    public boolean run() {
        sender.sendMessage(ChatColor.AQUA + "> Reloading plugin...");
        plugin.reload();
        sender.sendMessage(ChatColor.GREEN + "> Reload Success <");

        return false;
    }
}