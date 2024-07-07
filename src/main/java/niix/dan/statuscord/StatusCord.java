package niix.dan.statuscord;

import niix.dan.statuscord.Command.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class StatusCord extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("statuscord").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
