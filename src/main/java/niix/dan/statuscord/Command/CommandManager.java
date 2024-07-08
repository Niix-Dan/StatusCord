package niix.dan.statuscord.Command;

import niix.dan.statuscord.Command.Cmds.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {
    private List<CmdBase> cmds = new ArrayList<CmdBase>();

    public CommandManager() {
        cmds.add(new info());
        cmds.add(new reload());
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        boolean ok = false;
        for (CmdBase cmd : cmds) {
            if (cmd.checkCmd(args)) {
                ok = true;
                getCommands(args[0]).proccesCmd(s, args);
            }
        }

        return ok;
    }


    public CmdBase getCommands(String s) {
        for(CmdBase cmd : cmds) {
            if(cmd.cmdName.equalsIgnoreCase(s)) {
                return cmd;
            }
        }
        return null;
    }

}
