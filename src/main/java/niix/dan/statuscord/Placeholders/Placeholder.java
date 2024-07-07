package niix.dan.statuscord.Placeholders;

import org.bukkit.entity.Player;

public interface Placeholder {
    String process(Player player, String params);
    String getName();
}