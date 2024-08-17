package de.hackv0gel.pluginAnalyse.commands;

import de.hackv0gel.pluginAnalyse.PluginAnalyse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AnalyseCommand implements CommandExecutor {

    private final PluginAnalyse plugin;

    public AnalyseCommand(PluginAnalyse plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(PluginAnalyse.Instance.Prefix + "§cUnknown command. §3Usage: /analyse <view|clear|checkethanol>.");
            return true;
        }

        if (args[0].equalsIgnoreCase("view")) {
            if (!sender.hasPermission("analyse.viewlogs")) {
                executeCreditsCommand(sender);
                return true;
            }

            try {
                List<String> logLines = Files.readAllLines(Paths.get(plugin.getDataFolder() + "/packets_log.txt"));
                if (logLines.isEmpty()) {
                    sender.sendMessage(PluginAnalyse.Instance.Prefix + "§cNo log data found.");
                } else {
                    sender.sendMessage("=== Logged Connections ===");
                    for (String line : logLines) {
                        sender.sendMessage(line);
                    }
                }
            } catch (IOException e) {
                sender.sendMessage(PluginAnalyse.Instance.Prefix + "§cError reading the log file.");
                e.printStackTrace();
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            if (!sender.hasPermission("analyse.clearlogs")) {
                executeCreditsCommand(sender);
                return true;
            }

            try {
                Files.write(Paths.get(plugin.getDataFolder() + "/packets_log.txt"), new byte[0]);
                sender.sendMessage(PluginAnalyse.Instance.Prefix + "§aLog file cleared.");
            } catch (IOException e) {
                sender.sendMessage(PluginAnalyse.Instance.Prefix + "§cError clearing the log file.");
                e.printStackTrace();
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("checkethanol")) {
            if (!sender.hasPermission("analyse.checkethanol")) {
                executeCreditsCommand(sender);
                return true;
            }

            if (plugin.getConfig().getBoolean("block-ethanol", false)) {
                try {
                    List<String> logLines = Files.readAllLines(Paths.get(plugin.getDataFolder() + "/packets_log.txt"));
                    boolean ethanolDetected = false;
                    for (String line : logLines) {
                        if (line.contains("ethanol.rocks") || line.contains("84.252.120.172")) {
                            ethanolDetected = true;
                            sender.sendMessage(PluginAnalyse.Instance.Prefix + "§4Ethanol connection found: " + line);
                        }
                    }
                    if (!ethanolDetected) {
                        sender.sendMessage(PluginAnalyse.Instance.Prefix + "§aNo connections to Ethanol servers found.");
                    }
                } catch (IOException e) {
                    sender.sendMessage(PluginAnalyse.Instance.Prefix + "§cError reading the log file.");
                    e.printStackTrace();
                }
            } else {
                sender.sendMessage(PluginAnalyse.Instance.Prefix + "§6Ethanol blocking is disabled. It is Recommended to enable this!");
            }
        }
        return true;
    }
    private void executeCreditsCommand(CommandSender sender) {
        plugin.getServer().dispatchCommand(sender, "analyse-credits");

    }
}