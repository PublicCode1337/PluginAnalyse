package de.hackv0gel.pluginAnalyse.utils;

import de.hackv0gel.pluginAnalyse.PluginAnalyse;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;

public class ConnectionListener extends ProxySelector {

    private final PluginAnalyse plugin;
    private final ProxySelector defaultSelector;

    public ConnectionListener(PluginAnalyse plugin) {
        this.plugin = plugin;
        this.defaultSelector = ProxySelector.getDefault();
    }

    @Override
    public List<Proxy> select(URI uri) {
        if (uri != null) {
            String host = uri.getHost();
            String pluginName = getPluginName();

            if (plugin.getConfig().getBoolean("block-ethanol", false)) {
                if (host.equalsIgnoreCase("ethanol.rocks") || host.equals("84.252.120.172")) {
                    plugin.logPacket("Blocked connection attempt to " + host + " by plugin " + pluginName);
                    plugin.sendDiscordWarning(pluginName);
                    return List.of(Proxy.NO_PROXY);
                }
            }

            String logMessage = "Attempting connection to: " + uri.toString();
            plugin.logPacket("[" + pluginName + "] " + logMessage);
        }
        return defaultSelector.select(uri);
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        String logMessage = "Connection failed to: " + uri.toString() + " at " + sa.toString() + " due to " + ioe.getMessage();
        String pluginName = getPluginName();
        plugin.logPacket("[" + pluginName + "] " + logMessage);
        defaultSelector.connectFailed(uri, sa, ioe);
    }

    private String getPluginName() {
        return "UnknownPlugin";
    }
}
