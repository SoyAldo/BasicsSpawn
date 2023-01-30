package com.soyaldo.basicsspawn.listeners;

import com.soyaldo.basicsspawn.BasicsSpawn;
import com.soyaldo.basicsspawn.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {

    private final BasicsSpawn basicsSpawn;

    public EntityDamage(BasicsSpawn basicsSpawn) {
        this.basicsSpawn = basicsSpawn;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        EntityDamageEvent.DamageCause damageCause = event.getCause();

        if (!damageCause.equals(EntityDamageEvent.DamageCause.VOID)) return;

        Entity entity = event.getEntity();
        EntityType entityType = event.getEntityType();

        if (!entityType.equals(EntityType.PLAYER)) return;

        boolean enable = basicsSpawn.getSettings().getFileConfiguration().getBoolean("void.enable", false);
        if (!enable) return;

        String format = basicsSpawn.getSettings().getFileConfiguration().getString("void.location", "undefined");
        Location location = LocationUtil.parse(format);

        if (location == null) return;

        entity.teleport(location);
        entity.setFallDistance(0);
        event.setDamage(0);
    }

}