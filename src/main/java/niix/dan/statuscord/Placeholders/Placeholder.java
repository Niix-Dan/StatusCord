package niix.dan.statuscord.Placeholders;

import org.bukkit.entity.Player;

public interface Placeholder {
    String process(String params);
    String getName();
}