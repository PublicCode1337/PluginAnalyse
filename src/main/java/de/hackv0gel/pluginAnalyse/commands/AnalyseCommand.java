package de.hackv0gel.pluginAnalyse.commands;

import de.hackv0gel.pluginAnalyse.PluginAnalyse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

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
            sender.sendMessage("Usage: /analyse <view|clear|checkethanol>.");
            return true;
        }

        if (args[0].equalsIgnoreCase("view")) {
            try {
                List<String> logLines = Files.readAllLines(Paths.get(plugin.getDataFolder() + "/packets_log.txt"));
                if (logLines.isEmpty()) {
                    sender.sendMessage("No log data found.");
                } else {
                    sender.sendMessage("=== Logged Connections ===");
                    for (String line : logLines) {
                        sender.sendMessage(line);
                    }
                }
            } catch (IOException e) {
                sender.sendMessage("Error reading the log file.");
                e.printStackTrace();
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            try {
                Files.write(Paths.get(plugin.getDataFolder() + "/packets_log.txt"), new byte[0]);
                sender.sendMessage("Log file cleared.");
            } catch (IOException e) {
                sender.sendMessage("Error clearing the log file.");
                e.printStackTrace();
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("checkethanol")) {
            if (plugin.getConfig().getBoolean("block-ethanol", false)) {
                try {
                    List<String> logLines = Files.readAllLines(Paths.get(plugin.getDataFolder() + "/packets_log.txt"));
                    boolean ethanolDetected = false;
                    for (String line : logLines) {
                        if (line.contains("ethanol.rocks") || line.contains("84.252.120.172")) {
                            ethanolDetected = true;
                            sender.sendMessage(ChatColor.RED + "Ethanol connection found: " + line);
                        }
                    }
                    if (!ethanolDetected) {
                        sender.sendMessage(ChatColor.GREEN + "No connections to Ethanol servers found.");
                    }
                } catch (IOException e) {
                    sender.sendMessage("Error reading the log file.");
                    e.printStackTrace();
                }
            } else {
                sender.sendMessage(ChatColor.YELLOW + "Ethanol blocking is disabled.");
            }
            return true;
        }

        sender.sendMessage("§cUnknown command. §3Usage: /analyse <view|clear|checkethanol>.");
        return true;
    }
}
