package com.soyaldo.basicsspawn.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class PlaceholderAPI {

    public static boolean isEnabled() {

        Server server = Bukkit.getServer();

        PluginManager pluginManager = server.getPluginManager();

        Plugin plugin = pluginManager.getPlugin( "PlaceholderAPI" );

        return ( plugin != null && plugin.isEnabled() );

    }

    public static String setPlaceholders( Player player , String text ) {
        if ( isEnabled() ) {
            return me.clip.placeholderapi.PlaceholderAPI.setPlaceholders( player , text );
        }
        return text;
    }

    public static String setPlaceholders( OfflinePlayer offlinePlayer , String text ) {
        if ( isEnabled() ) {
            return me.clip.placeholderapi.PlaceholderAPI.setPlaceholders( offlinePlayer , text );
        }
        return text;
    }

}