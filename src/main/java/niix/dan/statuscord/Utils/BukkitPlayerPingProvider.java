package niix.dan.statuscord.Utils;

import com.google.common.collect.ImmutableMap;
import niix.dan.statuscord.Monitor.Ping.PlayerPingProvider;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Map;

public class BukkitPlayerPingProvider implements PlayerPingProvider {

    public static boolean isSupported() {
        try {
            Player.Spigot.class.getMethod("getPing");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private final Server server;

    public BukkitPlayerPingProvider(Server server) {
        this.server = server;
    }

    @Override
    public Map<String, Integer> poll() {
        ImmutableMap.Builder<String, Integer> builder = ImmutableMap.builder();
        for (Player player : this.server.getOnlinePlayers()) {
            builder.put(player.getName(), player.getPing());
        }
        return builder.build();
    }
}
