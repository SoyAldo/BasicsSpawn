package com.soyaldo.basicsspawn.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Send {

    public static void message(CommandSender commandSender, Object message) {

        message(commandSender, message, new String[][]{});

    }

    public static void message(CommandSender commandSender, Object message, String[][] replacements) {
        if (commandSender instanceof ConsoleCommandSender) {
            message((ConsoleCommandSender) commandSender, message, replacements);
        } else {
            message((Player) commandSender, message, replacements);
        }
    }

    public static void message(CommandSender commandSender, List<String> message) {
        message(commandSender, message, new String[][]{});
    }

    public static void message(CommandSender commandSender, List<String> message, String[][] replacements) {
        if (commandSender instanceof ConsoleCommandSender) {
            message((ConsoleCommandSender) commandSender, message, replacements);
        } else {
            message((Player) commandSender, message, replacements);
        }
    }

    public static void message(CommandSender commandSender, String message) {
        message(commandSender, message, new String[][]{});
    }

    public static void message(CommandSender commandSender, String message, String[][] replacements) {
        if (commandSender instanceof ConsoleCommandSender) {
            message((ConsoleCommandSender) commandSender, message, replacements);
        } else {
            message((Player) commandSender, message, replacements);
        }
    }

    public static void message(Player player, Object message) {
        message(player, message, new String[][]{});
    }

    public static void message(Player player, Object message, String[][] replacements) {
        if (message == null) return;
        if (message.getClass().getSimpleName().equals("String")) {
            message(player, (String) message, replacements);
        } else {
            message(player, (List<String>) message, replacements);
        }
    }

    public static void message(Player player, List<String> messages) {
        message(player, messages, new String[][]{});
    }

    public static void message(Player player, List<String> messages, String[][] replacements) {
        if (messages == null) return;
        for (String message : messages) {
            message(player, message, replacements);
        }
    }

    public static void message(Player player, String message) {
        message(player, message, new String[][]{});
    }

    public static void message(Player player, String message, String[][] replacements) {

        if (message == null) return;

        String finalMessage = PlaceholderAPI.setPlaceholders(player, message);

        for (String[] replacement : replacements) {
            finalMessage = finalMessage.replaceAll(replacement[0], replacement[1]);
        }

        finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);

        player.sendMessage(finalMessage);

    }

    public static void message(ConsoleCommandSender consoleCommandSender, Object message) {
        message(consoleCommandSender, message, new String[][]{});
    }

    public static void message(ConsoleCommandSender consoleCommandSender, Object message, String[][] replacements) {
        if (message == null) return;
        if (message.getClass().getSimpleName().equals("String")) {
            message(consoleCommandSender, (String) message, replacements);
        } else {
            message(consoleCommandSender, (List<String>) message, replacements);
        }
    }

    public static void message(ConsoleCommandSender consoleCommandSender, List<String> messages) {
        message(consoleCommandSender, messages, new String[][]{});
    }

    public static void message(ConsoleCommandSender consoleCommandSender, List<String> messages, String[][] replacements) {
        if (messages == null) return;
        for (String message : messages) {
            message(consoleCommandSender, message, replacements);
        }
    }

    public static void message(ConsoleCommandSender consoleCommandSender, String message) {
        message(consoleCommandSender, message, new String[][]{});
    }

    public static void message(ConsoleCommandSender consoleCommandSender, String message, String[][] replacements) {

        if (message == null) return;

        String finalMessage = message;

        for (String[] replacement : replacements) {
            finalMessage = finalMessage.replaceAll(replacement[0], replacement[1]);
        }

        finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);

        consoleCommandSender.sendMessage(finalMessage);

    }

}