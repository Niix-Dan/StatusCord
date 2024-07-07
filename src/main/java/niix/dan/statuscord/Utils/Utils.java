package niix.dan.statuscord.Utils;

import niix.dan.statuscord.Monitor.Ping.PlayerPingProvider;

import static org.bukkit.Bukkit.getServer;

public class Utils {

    /**
     * Replaces placeholders in the format {hook} with their corresponding values.
     *
     * @param message The message containing the placeholders.
     * @return The message with placeholders replaced by their corresponding values.
     */
    public static String filter(String message) {
        for (ConfigEnum config : ConfigEnum.values()) {
            try {
                message = message.replace("{" + config.getHook() + "}", config.getResult());
            } catch (Exception e) {
                // Log the exception or handle it appropriately
                System.err.println("Error getting result for hook: " + config.getHook() + " - " + e.getMessage());
            }
        }
        return message;
    }
}
