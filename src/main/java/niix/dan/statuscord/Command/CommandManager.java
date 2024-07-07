package niix.dan.statuscord.Command;

import niix.dan.statuscord.Command.Cmds.Teste;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {
    private List<CmdBase> cmds = new ArrayList<CmdBase>();

    public CommandManager() {
        cmds.add(new Teste());
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        for (CmdBase cmd : cmds) {
            if (cmd.checkCmd(args)) {
                getCommands(args[0]).proccesCmd(s, args);
            }
        }

        return false;
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
