package niix.dan.statuscord;

import niix.dan.statuscord.Command.CommandManager;
import niix.dan.statuscord.Discord.DiscordManager;
import niix.dan.statuscord.Monitor.Tick.StcordTickStatistics;
import niix.dan.statuscord.Monitor.Tick.TickStatistics;
import niix.dan.statuscord.Placeholders.PapiExpansion;
import okhttp3.OkHttpClient;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class StatusCord extends JavaPlugin {
    public static StatusCord plugin;
    public static TickStatistics tickStatistics;
    public static long serverUptime = System.currentTimeMillis();
    public DiscordManager dc;

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        reloadConfig();

        getCommand("statuscord").setExecutor(new CommandManager());

        tickStatistics = new StcordTickStatistics();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PapiExpansion().register();
        }

        runBot();
    }

    private void runBot() {
        boolean canRun = true;
        if(!getConfig().getString("Discord.BotToken", null).isEmpty()) {
            Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "> BotToken found!");
        } else {
            Bukkit.getLogger().log(Level.WARNING, ChatColor.RED + "> BotToken not found! Please check your config.yml and restart");
            canRun = false;
        }

        if(!getConfig().getString("Discord.ChannelId", null).isEmpty()) {
            Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "> ChannelId found!");
        } else {
            Bukkit.getLogger().log(Level.WARNING, ChatColor.RED + "> ChannelId not found! Please check your config.yml and restart");
            canRun = false;
        }

        if(canRun) {
            dc = new DiscordManager(plugin, getConfig().getString("Discord.BotToken", null), getConfig().getString("Discord.ChannelId", null));
        }
    }

    public void reload() {
        Bukkit.getScheduler().cancelTasks(plugin);

        dc.getJda().shutdownNow();
        OkHttpClient client = dc.getJda().getHttpClient();
        client.connectionPool().evictAll();
        client.dispatcher().executorService().shutdown();

        reloadConfig();
        runBot();
    }
}
