package de.hackv0gel.pluginAnalyse.commands;

import de.hackv0gel.pluginAnalyse.PluginAnalyse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CreditsCommand implements CommandExecutor {


    private final PluginAnalyse plugin;

    public CreditsCommand(PluginAnalyse plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String creditsMessage = ChatColor.GOLD + "=== Plugin Credits ===\n" +
                "§6Developer: §aHackv0gel\n" +
                "§6Version: " + PluginAnalyse.Instance.Version + "\n" +
                "§6Description: §aAnalyse Connections from all Plugins!\n" +
                "§7Download: https://github.com/PublicCode1337/PluginAnalyse\n" +
                "§dThanks for using this Plugin <3";

        commandSender.sendMessage(creditsMessage);

        return true;
    }
}
