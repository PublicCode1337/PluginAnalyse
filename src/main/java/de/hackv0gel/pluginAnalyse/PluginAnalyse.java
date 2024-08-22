package de.hackv0gel.pluginAnalyse;

import de.hackv0gel.pluginAnalyse.commands.AnalyseCommand;
import de.hackv0gel.pluginAnalyse.commands.CreditsCommand;
import de.hackv0gel.pluginAnalyse.utils.ConnectionListener;
import de.hackv0gel.pluginAnalyse.utils.DiscordWebhook;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.net.ProxySelector;

public final class PluginAnalyse extends JavaPlugin {

    private BufferedWriter writer;
    public static PluginAnalyse Instance;

    public String Version = "1.4";
    public String Prefix = "§9§lPlugin§b§lAnalyse §7»§r ";

    @Override
    public void onEnable() {
        Instance = this;
        saveDefaultConfig();

        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }

            File logFile = new File(getDataFolder(), "packets_log.txt");

            writer = new BufferedWriter(new FileWriter(logFile, true));
            getLogger().info("PacketLogger enabled!");
            this.getCommand("analyse").setExecutor(new AnalyseCommand(this));
            this.getCommand("analyse-credits").setExecutor(new CreditsCommand(this));

            ProxySelector.setDefault(new ConnectionListener(this));

            if (getConfig().getBoolean("block-ethanol", false)) {
                getLogger().warning("Ethanol blocking is disabled.");
            }
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not create log file!", e);
        }
    }

    @Override
    public void onDisable() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not close log file!", e);
        }
        getLogger().info("PacketLogger disabled!");
    }

    public void logPacket(String data) {
        try {
            writer.write(data + "\n");
            writer.flush();
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not write to log file!", e);
        }
    }

    public void sendDiscordWarning(String pluginName) {
        String webhookUrl = getConfig().getString("discord-webhook-url", "");
        if (!webhookUrl.isEmpty()) {
            String message = "@everyone Ethanol detected and Blocked! (" + pluginName + ")";
            DiscordWebhook.send(webhookUrl, message);
        }
    }
}
