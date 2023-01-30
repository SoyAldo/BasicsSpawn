package com.soyaldo.basicsspawn;

import com.soyaldo.basicsspawn.commands.Spawn;
import com.soyaldo.basicsspawn.commands.SpawnAdmin;
import com.soyaldo.basicsspawn.listeners.EntityDamage;
import com.soyaldo.basicsspawn.listeners.PlayerJoin;
import com.soyaldo.basicsspawn.listeners.PlayerRespawn;
import com.soyaldo.basicsspawn.util.BrandSender;
import com.soyaldo.basicsspawn.util.Metrics;
import com.soyaldo.basicsspawn.util.UpdateChecker;
import com.soyaldo.basicsspawn.util.Yaml;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasicsSpawn extends JavaPlugin {

    private final Yaml settings = new Yaml(this, "settings");
    private Yaml lang;

    private final UpdateChecker updateChecker = new UpdateChecker(this, 107590);

    @Override
    public void onEnable() {
        // Files
        settings.register();
        lang = new Yaml(this, "lang", settings.getFileConfiguration().getString("lang", "en_us"));
        lang.register();
        // Commands
        new Spawn(this).registerCommand(this);
        new SpawnAdmin(this).registerCommand(this);
        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(this), this);
        // B Stats
        Metrics metrics = new Metrics(this, 17527);
        // BrandSender
        BrandSender.sendVersionStatusFromConsole(this, "&aEnabled");

        if (updateChecker.getUpdateCheckResult().equals(UpdateChecker.UpdateCheckResult.OUT_DATED)) {
            updateChecker.sendOutDatedMessage(getServer().getConsoleSender());
        }
    }

    @Override
    public void onDisable() {
        BrandSender.sendVersionStatusFromConsole(this, "&cDisabled");
    }

    public void onReload() {
        settings.reload();
        lang = new Yaml(this, "lang", settings.getFileConfiguration().getString("lang", "en_us"));
        lang.register();
    }

    public Yaml getSettings() {
        return settings;
    }

    public Yaml getLang() {
        return lang;
    }
}