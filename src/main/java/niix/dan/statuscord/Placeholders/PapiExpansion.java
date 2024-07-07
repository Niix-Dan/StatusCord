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
        return "stcord";
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
            return placeholder.process(data);
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
        registerPlaceholder(new Pmanager(this, "cpumodel"));
        registerPlaceholder(new Pmanager(this, "procload"));
        registerPlaceholder(new Pmanager(this, "procload_10s"));
        registerPlaceholder(new Pmanager(this, "procload_1m"));
        registerPlaceholder(new Pmanager(this, "procload_15m"));
        registerPlaceholder(new Pmanager(this, "sysload"));
        registerPlaceholder(new Pmanager(this, "sysload_10s"));
        registerPlaceholder(new Pmanager(this, "sysload_1m"));
        registerPlaceholder(new Pmanager(this, "sysload_15m"));
        registerPlaceholder(new Pmanager(this, "disk_used"));
        registerPlaceholder(new Pmanager(this, "disk_total"));
        registerPlaceholder(new Pmanager(this, "disk_free"));
        registerPlaceholder(new Pmanager(this, "physical_used"));
        registerPlaceholder(new Pmanager(this, "physical_total"));
        registerPlaceholder(new Pmanager(this, "physical_free"));
        registerPlaceholder(new Pmanager(this, "swap_used"));
        registerPlaceholder(new Pmanager(this, "swap_total"));
        registerPlaceholder(new Pmanager(this, "swap_free"));
        registerPlaceholder(new Pmanager(this, "virtual_total"));
        registerPlaceholder(new Pmanager(this, "tps_5s"));
        registerPlaceholder(new Pmanager(this, "tps_10s"));
        registerPlaceholder(new Pmanager(this, "tps_1m"));
        registerPlaceholder(new Pmanager(this, "tps_5m"));
        registerPlaceholder(new Pmanager(this, "tps_15m"));
        registerPlaceholder(new Pmanager(this, "online"));
        registerPlaceholder(new Pmanager(this, "offline"));
        registerPlaceholder(new Pmanager(this, "max"));
        registerPlaceholder(new Pmanager(this, "ping"));

    };
}
