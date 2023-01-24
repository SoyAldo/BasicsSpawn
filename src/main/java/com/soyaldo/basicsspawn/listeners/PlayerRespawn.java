package com.soyaldo.basicsspawn.listeners;

import com.soyaldo.basicsspawn.BasicsSpawn;
import com.soyaldo.basicsspawn.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    private final BasicsSpawn basicsSpawn;

    public PlayerRespawn(BasicsSpawn basicsSpawn) {
        this.basicsSpawn = basicsSpawn;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        boolean enable = basicsSpawn.getSettings().getFileConfiguration().getBoolean("respawn.enable", false);
        if (!enable) return;

        boolean overrideBed = basicsSpawn.getSettings().getFileConfiguration().getBoolean("respawn.overrideBed", false);
        if (event.isBedSpawn()) {
            if (!overrideBed) {
                return;
            }
        }

        String format = basicsSpawn.getSettings().getFileConfiguration().getString("respawn.location", "undefined");
        Location location = LocationUtil.parse(format);

        if (location == null) return;
        event.setRespawnLocation(location);
    }

}