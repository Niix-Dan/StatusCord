package niix.dan.statuscord.Placeholders;


import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.TreeMap;

public class PapiExpansion extends PlaceholderExpansion {
    private final Map<String, Placeholder> placeholders = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public PapiExpansion() {
        init();
    }

    @Override
    public String getIdentifier() {
        return "statuscord";
    }

    @Override
    public String getAuthor() {
        return "Niix-Dan";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String getRequiredPlugin() {
        return "StatusCord";
    }

    @Override
    @Nullable
    public String onPlaceholderRequest(final Player player, @NotNull final String params) {
        String token;
        String data = null;
        int dataPosition = params.indexOf(":");

        if (dataPosition != -1) {
            token = params.substring(0, dataPosition);
            data = params.substring(dataPosition + 1);
        } else {
            token = params;
        }

        Placeholder placeholder = placeholders.get(token);

        if (placeholder != null) {
            return placeholder.process(player, data);
        } else {
            return null;
        }
    }

    public void registerPlaceholder(Placeholder placeholder) {
        final Placeholder registered = placeholders.get(placeholder.getName());
        if (registered != null)
            throw new IllegalStateException("Placeholder " + placeholder.getName() + " is already registered!");

        placeholders.put(placeholder.getName(), placeholder);
    }

    protected void init() {
        // RegisterPlaceholders here
    };
}
