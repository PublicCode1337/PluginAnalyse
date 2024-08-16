package de.hackv0gel.pluginAnalyse.commands;

import de.hackv0gel.pluginAnalyse.PluginAnalyse;
import de.hackv0gel.pluginAnalyse.utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            ChatUtil.unknownCommand((Player) sender);
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
                    sender.sendMessage("No log data found.");
                } else {
                    sender.sendMessage("=== Logged Connections ===");
                    for (String line : logLines) {
                        sender.sendMessage(line);
                    }
                }
            } catch (IOException e) {
                sender.sendMessage(ChatColor.RED + "Error reading the log file.");
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
                ChatUtil.clearLogfileSucess((Player) sender);
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
                        ChatUtil.noEthanolConnections((Player) sender);
                    }
                } catch (IOException e) {
                    ChatUtil.errorreadlogfile((Player) sender);
                    e.printStackTrace();
                }
            } else {
                ChatUtil.ethanolBlockdis((Player) sender);
            }
        }

        ChatUtil.unknownCommand((Player) sender);
        return true;
    }

    private void executeCreditsCommand(CommandSender sender) {
        plugin.getServer().dispatchCommand(sender, "analyse-credits");

    }
}
