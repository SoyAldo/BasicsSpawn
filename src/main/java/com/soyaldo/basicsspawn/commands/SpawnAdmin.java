package com.soyaldo.basicsspawn.commands;

import com.soyaldo.basicsspawn.BasicsSpawn;
import com.soyaldo.basicsspawn.util.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnAdmin extends Command {

    private final BasicsSpawn basicsSpawn;

    public SpawnAdmin(BasicsSpawn basicsSpawn) {
        super("basicsspawnadmin");
        this.basicsSpawn = basicsSpawn;
    }

    @Override
    public void onPlayerExecute(Player sender, String[] args) {
        Yaml lang = basicsSpawn.getLang();
        FileConfiguration langConfiguration = lang.getFileConfiguration();
        Yaml settings = basicsSpawn.getSettings();
        FileConfiguration settingsConfiguration = settings.getFileConfiguration();

        if (!sender.hasPermission("basicsspawn.admin")) {
            Send.message(
                    sender,
                    langConfiguration.get("admin.noPermission", "&cThe path &8'&7admin.noPermission&8' &cis undefined.")
            );
            return;
        }

        if (args.length < 1) {
            Send.message(
                    sender,
                    langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined.")
            );
            return;
        }

        switch (args[0].toLowerCase()) {
            case "help": {
                Send.message(
                        sender,
                        langConfiguration.get("admin.help", "&cThe path &8'&7admin.help&8' &cis undefined.")
                );
                break;
            }
            case "firstjoin": {
                if (args.length < 2) {
                    Send.message(sender, langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined."));
                    return;
                }

                switch (args[1].toLowerCase()) {
                    case "toggle": {
                        boolean status = settings.getFileConfiguration().getBoolean("firstJoin.enable", false);
                        if (status) {
                            settings.getFileConfiguration().set("firstJoin.enable", false);
                            Send.message(sender, langConfiguration.get("admin.firstJoinDisabled", "&cThe path &8'&7admin.firstJoinDisabled&8' &cis undefined."));
                        } else {
                            settings.getFileConfiguration().set("firstJoin.enable", true);
                            Send.message(sender, langConfiguration.get("admin.firstJoinEnabled", "&cThe path &8'&7admin.firstJoinEnabled&8' &cis undefined."));
                        }
                        settings.save();
                        return;
                    }
                    case "setlocation": {
                        settings.getFileConfiguration().set("firstJoin.location", LocationUtil.parse(sender.getLocation(), true));
                        settings.save();
                        Send.message(sender, langConfiguration.get("admin.firstJoinEstablished", "&cThe path &8'&7admin.firstJoinEstablished&8' &cis undefined."));
                        return;
                    }
                    default: {
                        Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[1]}});
                        return;
                    }
                }
            }
            case "onjoin": {
                if (args.length < 2) {
                    Send.message(sender, langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined."));
                    return;
                }

                switch (args[1].toLowerCase()) {
                    case "toggle": {
                        boolean status = settings.getFileConfiguration().getBoolean("onJoin.enable", false);
                        if (status) {
                            settings.getFileConfiguration().set("onJoin.enable", false);
                            Send.message(sender, langConfiguration.get("admin.onJoinDisabled", "&cThe path &8'&7admin.onJoinDisabled&8' &cis undefined."));
                        } else {
                            settings.getFileConfiguration().set("onJoin.enable", true);
                            Send.message(sender, langConfiguration.get("admin.onJoinEnabled", "&cThe path &8'&7admin.onJoinEnabled&8' &cis undefined."));
                        }
                        settings.save();
                        return;
                    }
                    case "setlocation": {
                        settings.getFileConfiguration().set("onJoin.location", LocationUtil.parse(sender.getLocation(), true));
                        settings.save();
                        Send.message(sender, langConfiguration.get("admin.onJoinEstablished", "&cThe path &8'&7admin.onJoinEstablished&8' &cis undefined."));
                        return;
                    }
                    default: {
                        Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[1]}});
                        return;
                    }
                }
            }
            case "respawn": {
                if (args.length < 2) {
                    Send.message(sender, langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined."));
                    return;
                }

                switch (args[1].toLowerCase()) {
                    case "toggle": {
                        boolean status = settings.getFileConfiguration().getBoolean("respawn.enable", false);
                        if (status) {
                            settings.getFileConfiguration().set("respawn.enable", false);
                            Send.message(sender, langConfiguration.get("admin.respawnDisabled", "&cThe path &8'&7admin.respawnDisabled&8' &cis undefined."));
                        } else {
                            settings.getFileConfiguration().set("respawn.enable", true);
                            Send.message(sender, langConfiguration.get("admin.respawnEnabled", "&cThe path &8'&7admin.respawnEnabled&8' &cis undefined."));
                        }
                        settings.save();
                        return;
                    }
                    case "toggleoverridebed": {
                        boolean status = settings.getFileConfiguration().getBoolean("respawn.overrideBed", false);
                        if (status) {
                            settings.getFileConfiguration().set("respawn.overrideBed", false);
                            Send.message(sender, langConfiguration.get("admin.respawnOverrideBedDisabled", "&cThe path &8'&7admin.respawnOverrideBedDisabled&8' &cis undefined."));
                        } else {
                            settings.getFileConfiguration().set("respawn.overrideBed", true);
                            Send.message(sender, langConfiguration.get("admin.respawnOverrideBedEnabled", "&cThe path &8'&7admin.respawnOverrideBedEnabled&8' &cis undefined."));
                        }
                        settings.save();
                        return;
                    }
                    case "setlocation": {
                        settings.getFileConfiguration().set("respawn.location", LocationUtil.parse(sender.getLocation(), true));
                        settings.save();
                        Send.message(sender, langConfiguration.get("admin.respawnEstablished", "&cThe path &8'&7admin.respawnEstablished&8' &cis undefined."));
                        return;
                    }
                    default: {
                        Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[1]}});
                        return;
                    }
                }
            }
            case "spawn": {
                if (args.length < 2) {
                    Send.message(sender, langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined."));
                    return;
                }

                if (!args[1].equalsIgnoreCase("setlocation")) {
                    Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[1]}});
                    return;
                }

                settings.getFileConfiguration().set("spawn.location", LocationUtil.parse(sender.getLocation(), true));
                settings.save();
                Send.message(sender, langConfiguration.get("admin.spawnEstablished", "&cThe path &8'&7admin.spawnEstablished&8' &cis undefined."));
                return;
            }
            case "status": {
                Send.message(
                        sender,
                        basicsSpawn.getLang().getFileConfiguration().get("admin.status", "&cThe path &8'&7admin.status&8' &cis undefined."),
                        new String[][]{
                                {"%firstJoinStatus%", String.valueOf(settingsConfiguration.getBoolean("firstJoin.enable", false))},
                                {"%firstJoinLocation%", settingsConfiguration.getString("firstJoin.location", "undefined").replaceAll(",", " ")},
                                {"%onJoinStatus%", String.valueOf(settingsConfiguration.getBoolean("onJoin.enable", false))},
                                {"%onJoinLocation%", settingsConfiguration.getString("onJoin.location", "undefined").replaceAll(",", " ")},
                                {"%respawnStatus%", String.valueOf(settingsConfiguration.getBoolean("respawn.enable", false))},
                                {"%respawnOverrideBed%", String.valueOf(settingsConfiguration.getBoolean("respawn.overrideBed", false))},
                                {"%respawnLocation%", settingsConfiguration.getString("respawn.location", "undefined").replaceAll(",", " ")},
                                {"%spawnLocation%", settingsConfiguration.getString("spawn.location", "undefined").replaceAll(",", " ")}
                        }
                );
                return;
            }
            case "reload": {
                basicsSpawn.onReload();
                Send.message(sender, basicsSpawn.getLang().getFileConfiguration().get("admin.reloaded", "&cThe path &8'&7admin.reloaded&8' &cis undefined."));
                return;
            }
            case "version": {
                BrandSender.sendVersion(basicsSpawn, sender);
                return;
            }
            default: {
                Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[0]}});

            }
        }
    }

    @Override
    public String onPlayerTabComplete(Player requester, int position, String[] previousArguments) {
        if (requester.hasPermission("basicsspawn.admin")) {
            switch (position) {
                case 1:
                    return "help,firstjoin,onjoin,respawn,spawn,status,reload,version";
                case 2:
                    switch (previousArguments[0]) {
                        case "firstjoin":
                        case "onjoin":
                            return "toggle,setlocation";
                        case "respawn":
                            return "toggle,toggleoverridebed,setlocation";
                        case "spawn":
                            return "setlocation";
                    }
            }
        }
        return "";
    }

    @Override
    public void onConsoleExecute(ConsoleCommandSender sender, String[] args) {
        Yaml lang = basicsSpawn.getLang();
        FileConfiguration langConfiguration = lang.getFileConfiguration();
        Yaml settings = basicsSpawn.getSettings();
        FileConfiguration settingsConfiguration = settings.getFileConfiguration();

        if (args.length < 1) {
            Send.message(sender, langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined."));
            return;
        }

        switch (args[0].toLowerCase()) {
            case "help": {
                Send.message(sender, langConfiguration.get("admin.help", "&cThe path &8'&7admin.help&8' &cis undefined."));
                break;
            }
            case "firstjoin": {
                if (args.length < 2) {
                    Send.message(sender, langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined."));
                    return;
                }

                switch (args[1].toLowerCase()) {
                    case "toggle": {
                        boolean status = settings.getFileConfiguration().getBoolean("firstJoin.enable", false);
                        if (status) {
                            settings.getFileConfiguration().set("firstJoin.enable", false);
                            Send.message(sender, langConfiguration.get("admin.firstJoinDisabled", "&cThe path &8'&7admin.firstJoinDisabled&8' &cis undefined."));
                        } else {
                            settings.getFileConfiguration().set("firstJoin.enable", true);
                            Send.message(sender, langConfiguration.get("admin.firstJoinEnabled", "&cThe path &8'&7admin.firstJoinEnabled&8' &cis undefined."));
                        }
                        settings.save();
                        return;
                    }
                    case "setlocation": {
                        Send.message(sender, langConfiguration.get("admin.onlyPlayersCanEstablishLocation", "&cThe path &8'&7admin.onlyPlayersCanEstablishLocation&8' &cis undefined."));
                        return;
                    }
                    default: {
                        Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[1]}});
                        return;
                    }
                }
            }
            case "onjoin": {
                if (args.length < 2) {
                    Send.message(sender, langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined."));
                    return;
                }

                switch (args[1].toLowerCase()) {
                    case "toggle": {
                        boolean status = settings.getFileConfiguration().getBoolean("onJoin.enable", false);
                        if (status) {
                            settings.getFileConfiguration().set("onJoin.enable", false);
                            Send.message(sender, langConfiguration.get("admin.onJoinDisabled", "&cThe path &8'&7admin.onJoinDisabled&8' &cis undefined."));
                        } else {
                            settings.getFileConfiguration().set("onJoin.enable", true);
                            Send.message(sender, langConfiguration.get("admin.onJoinEnabled", "&cThe path &8'&7admin.onJoinEnabled&8' &cis undefined."));
                        }
                        settings.save();
                        return;
                    }
                    case "setlocation": {
                        Send.message(sender, langConfiguration.get("admin.onlyPlayersCanEstablishLocation", "&cThe path &8'&7admin.onlyPlayersCanEstablishLocation&8' &cis undefined."));
                        return;
                    }
                    default: {
                        Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[1]}});
                        return;
                    }
                }
            }
            case "respawn": {
                if (args.length < 2) {
                    Send.message(sender, langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined."));
                    return;
                }

                switch (args[1].toLowerCase()) {
                    case "toggle": {
                        boolean status = settings.getFileConfiguration().getBoolean("respawn.enable", false);
                        if (status) {
                            settings.getFileConfiguration().set("respawn.enable", false);
                            Send.message(sender, langConfiguration.get("admin.respawnDisabled", "&cThe path &8'&7admin.respawnDisabled&8' &cis undefined."));
                        } else {
                            settings.getFileConfiguration().set("respawn.enable", true);
                            Send.message(sender, langConfiguration.get("admin.respawnEnabled", "&cThe path &8'&7admin.respawnEnabled&8' &cis undefined."));
                        }
                        settings.save();
                        return;
                    }
                    case "toggleoverridebed": {
                        boolean status = settings.getFileConfiguration().getBoolean("respawn.overrideBed", false);
                        if (status) {
                            settings.getFileConfiguration().set("respawn.overrideBed", false);
                            Send.message(sender, langConfiguration.get("admin.respawnOverrideBedDisabled", "&cThe path &8'&7admin.respawnOverrideBedDisabled&8' &cis undefined."));
                        } else {
                            settings.getFileConfiguration().set("respawn.overrideBed", true);
                            Send.message(sender, langConfiguration.get("admin.respawnOverrideBedEnabled", "&cThe path &8'&7admin.respawnOverrideBedEnabled&8' &cis undefined."));
                        }
                        settings.save();
                        return;
                    }
                    case "setlocation": {
                        Send.message(sender, langConfiguration.get("admin.onlyPlayersCanEstablishLocation", "&cThe path &8'&7admin.onlyPlayersCanEstablishLocation&8' &cis undefined."));
                        return;
                    }
                    default: {
                        Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[1]}});
                        return;
                    }
                }
            }
            case "spawn": {
                if (args.length < 2) {
                    Send.message(sender, langConfiguration.get("admin.emptyOption", "&cThe path &8'&7admin.emptyOption&8' &cis undefined."));
                    return;
                }

                if (!args[1].equalsIgnoreCase("setlocation")) {
                    Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[1]}});
                    return;
                }

                Send.message(sender, langConfiguration.get("admin.onlyPlayersCanEstablishLocation", "&cThe path &8'&7admin.onlyPlayersCanEstablishLocation&8' &cis undefined."));
                return;
            }
            case "status": {
                Send.message(
                        sender,
                        basicsSpawn.getLang().getFileConfiguration().get("admin.status", "&cThe path &8'&7admin.status&8' &cis undefined."),
                        new String[][]{
                                {"%firstJoinStatus%", String.valueOf(settingsConfiguration.getBoolean("firstJoin.enable", false))},
                                {"%firstJoinLocation%", settingsConfiguration.getString("firstJoin.location", "undefined").replaceAll(",", " ")},
                                {"%onJoinStatus%", String.valueOf(settingsConfiguration.getBoolean("onJoin.enable", false))},
                                {"%onJoinLocation%", settingsConfiguration.getString("onJoin.location", "undefined").replaceAll(",", " ")},
                                {"%respawnStatus%", String.valueOf(settingsConfiguration.getBoolean("respawn.enable", false))},
                                {"%respawnOverrideBed%", String.valueOf(settingsConfiguration.getBoolean("respawn.overrideBed", false))},
                                {"%respawnLocation%", settingsConfiguration.getString("respawn.location", "undefined").replaceAll(",", " ")},
                                {"%spawnLocation%", settingsConfiguration.getString("spawn.location", "undefined").replaceAll(",", " ")}
                        }
                );
                return;
            }
            case "reload": {
                basicsSpawn.onReload();
                Send.message(sender, basicsSpawn.getLang().getFileConfiguration().get("admin.reloaded", "&cThe path &8'&7admin.reloaded&8' &cis undefined."));
                return;
            }
            case "version": {
                BrandSender.sendVersion(basicsSpawn, sender);
                return;
            }
            default: {
                Send.message(sender, langConfiguration.get("admin.invalidOption", "&cThe path &8'&7admin.invalidOption&8' &cis undefined."), new String[][]{{"%option%", args[0]}});
            }
        }
    }

    @Override
    public String onConsoleTabComplete(ConsoleCommandSender requester, int position, String[] previousArguments) {
        switch (position) {
            case 1:
                return "help,firstjoin,onjoin,respawn,spawn,status,reload,version";
            case 2:
                switch (previousArguments[0]) {
                    case "firstjoin":
                    case "onjoin":
                        return "toggle,setlocation";
                    case "respawn":
                        return "toggle,toggleoverridebed,setlocation";
                    case "spawn":
                        return "setlocation";
                }
        }
        return "";
    }

}