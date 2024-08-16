package de.hackv0gel.pluginAnalyse.utils;

import de.hackv0gel.pluginAnalyse.PluginAnalyse;
import de.hackv0gel.pluginAnalyse.commands.AnalyseCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil {


    public static void sendMessage(Player player, String message) {
        if (player != null && message != null) {
            player.sendMessage(PluginAnalyse.Instance.Prefix + message);
        }
    }

    public static void noPrefix(Player player, String message){
        if (player != null && message != null){
            player.sendMessage(message);
        }
    }

    public static void unknownCommand(Player player){
        player.sendMessage(PluginAnalyse.Instance.Prefix + "§cUnknown command. §3Usage: /analyse <view|clear|checkethanol>.");
    }

    public static void ethanolBlockdis(Player player){
        player.sendMessage(PluginAnalyse.Instance.Prefix + "§6Ethanol blocking is disabled. It is Recommended to enable this!");
    }

    public static void errorreadlogfile(Player player){
        player.sendMessage(PluginAnalyse.Instance.Prefix + "§cError reading the log file.");
    }

    public static void noEthanolConnections(Player player){
        player.sendMessage(PluginAnalyse.Instance.Prefix + "§aNo connections to Ethanol servers found.");
    }

    public static void clearLogfileSucess(Player player) {
        player.sendMessage(PluginAnalyse.Instance.Prefix + "§aLog file cleared.");
    }
}
