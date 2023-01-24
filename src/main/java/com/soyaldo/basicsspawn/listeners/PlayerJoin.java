package com.soyaldo.basicsspawn.listeners;

import com.soyaldo.basicsspawn.BasicsSpawn;
import com.soyaldo.basicsspawn.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final BasicsSpawn basicsSpawn;

    public PlayerJoin(BasicsSpawn basicsSpawn) {
        this.basicsSpawn = basicsSpawn;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPlayedBefore()) {
            boolean enable = basicsSpawn.getSettings().getFileConfiguration().getBoolean("onJoin.enable", false);
            if (!enable) return;

            String format = basicsSpawn.getSettings().getFileConfiguration().getString("onJoin.location", "undefined");
            Location location = LocationUtil.parse(format);

            if (location == null) return;
            player.teleport(location);
            return;
        }

        boolean enable = basicsSpawn.getSettings().getFileConfiguration().getBoolean("firstJoin.enable", false);
        if (!enable) return;

        String format = basicsSpawn.getSettings().getFileConfiguration().getString("firstJoin.location", "undefined");
        Location location = LocationUtil.parse(format);

        if (location == null) return;
        player.teleport(location);
    }

}