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
    private TextChannel alertChannel = null;
    private String lastMessageId = "";
    private final FileConfiguration config;
    private final String channelId;
    public int task = 0;
    public int tpstask = 0;
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
        if(!config.getString("TpsAlerter.ChannelId").isEmpty()) this.alertChannel = this.jda.getTextChannelById(config.getString("TpsAlerter.ChannelId");
        this.channel = this.jda.getTextChannelById(this.channelId);

        tpstask = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> checkTps(),
                0l, config.getInt("TpsAlerter.CheckRate", 5) * 20L);
        task = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, () -> checkAndProcessMessages(),
                0l, config.getInt("Board.Timeout", 8) * 20L);
    }

    public void sendOrEditMessage(TextChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription(filter(config.getString("Board.Embed.Description", "> Invalid embed description! Please check your ` config.yml `")));
        embed.setColor(Color.decode(config.getString("Board.Embed.Color", "#FFFFFF")));
        embed.setFooter("[" + LocalTime.now() + "]");

        try {
            if (!lastMessageId.isEmpty()) {
                //channel.editMessageEmbedsById(lastMessageId, embed.build()).queue();
                channel.retrieveMessageById(lastMessageId).queue(
                        message -> {
                            channel.editMessageEmbedsById(lastMessageId, embed.build()).queue();
                        },
                        failure -> {
                            channel.sendMessageEmbeds(embed.build()).queue(sentMessage -> {
                                lastMessageId = sentMessage.getId();
                                this.config.set("Cache.MessageId", lastMessageId);
                                plugin.saveConfig();
                            });
                        }
                );
            } else {
                channel.sendMessageEmbeds(embed.build()).queue(sentMessage -> {
                    lastMessageId = sentMessage.getId();
                    this.config.set("Cache.MessageId", lastMessageId);
                    plugin.saveConfig();
                });
            }
        } catch (Exception ex) { }
    }

    public void checkTps() {
        String content = filter(config.getString("TpsAlerter.Content", "||@everyone||"));
        if(tps > config.getInt("TpsAlerter.Tps", 5)) return;
        // TODO: TpsChecker
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription(filter(config.getString("TpsAlerter.Embed.Description", "### LOW TPS\n> Invalid embed description! Please check your ` config.yml `")));
        embed.setColor(Color.decode(config.getString("TpsAlerter.Embed.Color", "#FFFFFF")));
        embed.setFooter("[" + LocalTime.now() + "]");

        alertChannel.sendMessageEmbeds(embed.build()).queue();
    }

    public void checkAndProcessMessages() {
        if (!channel.getLatestMessageId().equalsIgnoreCase(lastMessageId)) {
            try {
                channel.deleteMessageById(lastMessageId).queue();
            } catch (Exception ex) { }
            lastMessageId = "";
        }

        sendOrEditMessage(channel);
    }

    public JDA getJda() {
        return jda;
    }
}
