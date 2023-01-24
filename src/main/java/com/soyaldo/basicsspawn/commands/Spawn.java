package com.soyaldo.basicsspawn.commands;

import com.soyaldo.basicsspawn.BasicsSpawn;
import com.soyaldo.basicsspawn.util.Command;
import com.soyaldo.basicsspawn.util.LocationUtil;
import com.soyaldo.basicsspawn.util.Send;
import com.soyaldo.basicsspawn.util.Yaml;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn extends Command {

    private final BasicsSpawn basicsSpawn;

    public Spawn(BasicsSpawn basicsSpawn) {
        super("basicsspawn");
        this.basicsSpawn = basicsSpawn;
    }

    @Override
    public void onPlayerExecute(Player sender, String[] args) {
        Yaml lang = basicsSpawn.getLang();
        FileConfiguration fileConfiguration = lang.getFileConfiguration();

        if (!sender.hasPermission("basicsspawn.spawn")) {
            Send.message(
                    sender,
                    fileConfiguration.get("spawn.noPermission", "&cThe path &8'&7spawn.noPermission&8' &cis undefined.")
            );
            return;
        }

        String format = basicsSpawn.getSettings().getFileConfiguration().getString("spawn.location", "undefined");
        Location location = LocationUtil.parse(format);

        if (location == null) {
            Send.message(
                    sender,
                    fileConfiguration.get("spawn.undefinedLocation", "&cThe path &8'&7spawn.undefinedLocation&8' &cis undefined.")
            );
            return;
        }

        if (args.length > 0) {

            String targetName = args[0];

            if (!targetName.equals(sender.getName())) {
                if (!sender.hasPermission("basicsspawn.spawn.other")) {
                    Send.message(
                            sender,
                            fileConfiguration.get("spawn.noPermissionOther", "&cThe path &8'&7spawn.noPermissionOther&8' &cis undefined.")
                    );
                    return;
                }

                Player target = basicsSpawn.getServer().getPlayerExact(targetName);

                if (target == null) {
                    Send.message(
                            sender,
                            fileConfiguration.get("spawn.invalidTarget", "&cThe path &8'&7spawn.invalidTarget&8' &cis undefined."),
                            new String[][]{
                                    {"%target%", targetName}
                            });
                    return;
                }

                target.teleport(location);
                Send.message(
                        sender,
                        fileConfiguration.get("spawn.teleportedOther", "&cThe path &8'&7spawn.teleportedOther&8' &cis undefined."),
                        new String[][]{
                                {"%target%", targetName}
                        });
                Send.message(
                        target,
                        fileConfiguration.get("spawn.teleported", "&cThe path &8'&7spawn.teleported&8' &cis undefined.")
                );
                return;
            }
        }

        sender.teleport(location);
        Send.message(
                sender,
                fileConfiguration.get("spawn.teleported", "&cThe path &8'&7spawn.teleported&8' &cis undefined.")
        );
    }

    @Override
    public void onConsoleExecute(ConsoleCommandSender sender, String[] args) {
        Yaml lang = basicsSpawn.getLang();
        FileConfiguration fileConfiguration = lang.getFileConfiguration();
        String format = basicsSpawn.getSettings().getFileConfiguration().getString("spawn.location", "undefined");
        Location location = LocationUtil.parse(format);

        if (location == null) {
            Send.message(
                    sender,
                    fileConfiguration.get("spawn.undefinedLocation", "&cThe path &8'&7spawn.undefinedLocation&8' &cis undefined.")
            );
            return;
        }

        if (args.length == 0) {
            Send.message(
                    sender,
                    fileConfiguration.get("spawn.emptyTarget", "&cThe path &8'&7spawn.emptyTarget&8' &cis undefined.")
            );
            return;
        }

        String targetName = args[0];
        Player target = basicsSpawn.getServer().getPlayerExact(targetName);

        if (target == null) {
            Send.message(
                    sender,
                    fileConfiguration.get("spawn.invalidTarget", "&cThe path &8'&7spawn.invalidTarget&8' &cis undefined."),
                    new String[][]{
                            {"%target%", targetName}
                    });
            return;
        }

        target.teleport(location);
        Send.message(
                sender,
                fileConfiguration.get("spawn.teleportedOther", "&cThe path &8'&7spawn.teleportedOther&8' &cis undefined."),
                new String[][]{
                        {"%target%", targetName}
                });
        Send.message(
                target,
                fileConfiguration.get("spawn.teleported", "&cThe path &8'&7spawn.teleported&8' &cis undefined.")
        );
    }

}