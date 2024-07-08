package niix.dan.statuscord.Command;

import niix.dan.statuscord.StatusCord;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class CmdBase {
    public CmdBase(){}
    public CommandSender sender;
    public String[] args;
    public String cmdName;
    public boolean forcePlayer = true;
    public String usageErr = "";
    public Player player;
    public String permission = "";
    public int argLength = 0;
    public StatusCord plugin = StatusCord.plugin;

    public boolean proccesCmd(CommandSender s, String[] _args) {
        sender = s;
        args = Arrays.stream(_args).skip(1).collect(Collectors.joining(" ")).split(" ");
        if(_args.length < argLength + 1) {
            return false;
        }

        if(!permission.isEmpty() && !s.hasPermission(permission) && !s.isOp()) {
            s.sendMessage(ChatColor.RED + "Insufficient Permissions.");
            return true;
        }

        if(forcePlayer){
            if (!(s instanceof Player)) {
                s.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }
        }


        run();
        return false;
    }

    public Boolean checkCmd(String[] _args) {

        if(_args.length >= argLength + 1) {
            String cmd = _args[0];
            args = Arrays.stream(_args).skip(1).collect(Collectors.joining(" ")).split(" ");

            if(cmdName.equalsIgnoreCase(cmd)) {
                return true;
            }

            return false;
        }
        return false;
    }


    public abstract boolean run();
}
