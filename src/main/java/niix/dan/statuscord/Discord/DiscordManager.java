package niix.dan.statuscord.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import niix.dan.statuscord.StatusCord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;
import java.time.LocalTime;
import java.util.logging.Level;

import static niix.dan.statuscord.Utils.Utils.filter;

public class DiscordManager extends ListenerAdapter {
    private JDA jda;
    private TextChannel channel = null;
    private String lastMessageId = "";
    private final FileConfiguration config;
    private final String channelId;
    private StatusCord plugin;

    public DiscordManager(StatusCord plugin, String token, String channelId) {
        this.config = plugin.getConfig();
        this.channelId = channelId;
        this.plugin = plugin;

        lastMessageId = plugin.getConfig().getString("Cache.MessageId", "");

        try {
            this.jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS)
                    .addEventListeners(this)
                    .build();
            plugin.getLogger().log(Level.INFO, ChatColor.GREEN + "JDA Login Successfully - Online");
        } catch (Exception ex) {
            plugin.getLogger().log(Level.WARNING, ChatColor.RED + "JDA Login Error: " + ex.getMessage());
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        this.channel = this.jda.getTextChannelById(this.channelId);

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, () -> checkAndProcessMessages(),
                0l,config.getInt("Board.Timeout", 3) * 20L);
    }

    public void sendOrEditMessage(TextChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription(filter(config.getString("Board.Embed.Description", "> Invalid embed description! Please check your ` config.yml `")));
        embed.setColor(Color.decode(config.getString("Board.Embed.Color", "#FFFFFF")));
        embed.setFooter("[" + LocalTime.now() + "]");

        try {
            if (!lastMessageId.isEmpty()) {
                channel.editMessageEmbedsById(lastMessageId, embed.build()).queue();
            } else {
                channel.sendMessageEmbeds(embed.build()).queue(sentMessage -> {
                    lastMessageId = sentMessage.getId();
                    this.config.set("Cache.MessageId", lastMessageId);
                    plugin.saveConfig();
                });
            }
        } catch (Exception ex) {
            //Bukkit.getLogger().log(Level.WARNING, ex.getMessage());
        }
    }

    public void checkAndProcessMessages() {
        try {
            channel.getHistory().retrievePast(1).queue(messages -> {
                if (messages.isEmpty()) {
                    lastMessageId = "";
                    sendOrEditMessage(channel);
                    return;
                }

                if (!channel.getLatestMessageId().equalsIgnoreCase(lastMessageId)) {
                    try {
                        channel.deleteMessageById(lastMessageId).submit();
                    } catch (Exception ex) {
                        //Bukkit.getLogger().log(Level.WARNING, "Failed to delete message: " + ex.getMessage());
                    }
                    lastMessageId = "";
                    sendOrEditMessage(channel);
                } else {
                    sendOrEditMessage(channel);
                }
            });
        } catch (Exception ex) {
            Bukkit.getLogger().log(Level.WARNING, "Error processing messages: " + ex.getMessage());
        }
    }

    public JDA getJda() {
        return jda;
    }
}
