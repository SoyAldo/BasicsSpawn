package com.soyaldo.basicsspawn.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class UpdateChecker {

    private final JavaPlugin javaPlugin;

    private int resourceId;
    private URL resourceURL;
    private String currentVersionString;
    private String latestVersionString;
    private UpdateCheckResult updateCheckResult;

    public UpdateChecker(JavaPlugin javaPlugin, int resourceId) {
        this.javaPlugin = javaPlugin;
        try {
            this.resourceId = resourceId;
            this.resourceURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId);
        } catch (Exception exception) {
            return;
        }

        currentVersionString = javaPlugin.getDescription().getVersion();
        latestVersionString = getLatestVersion();

        if (latestVersionString == null) {
            updateCheckResult = UpdateCheckResult.NO_RESULT;
            return;
        }

        int currentVersion = Integer.parseInt(currentVersionString.split("-")[0].replace(".", ""));
        int latestVersion = Integer.parseInt(getLatestVersion().split("-")[0].replace(".", ""));

        if (currentVersion < latestVersion) updateCheckResult = UpdateCheckResult.OUT_DATED;
        else if (currentVersion == latestVersion) updateCheckResult = UpdateCheckResult.UP_TO_DATE;
        else updateCheckResult = UpdateCheckResult.UNRELEASED;
    }

    public enum UpdateCheckResult {
        NO_RESULT, OUT_DATED, UP_TO_DATE, UNRELEASED,
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getResourceURL() {
        return "https://www.spigotmc.org/resources/" + resourceId;
    }

    public String getCurrentVersionString() {
        return currentVersionString;
    }

    public String getLatestVersionString() {
        return latestVersionString;
    }

    public UpdateCheckResult getUpdateCheckResult() {
        return updateCheckResult;
    }

    public String getLatestVersion() {
        try {
            URLConnection urlConnection = resourceURL.openConnection();
            return new BufferedReader(new InputStreamReader(urlConnection.getInputStream())).readLine();
        } catch (Exception exception) {
            return null;
        }
    }

    public void sendOutDatedMessage(CommandSender commandSender) {
        List<String> message = new ArrayList<>();
        message.add("&6|");
        message.add("&6| &eThe plugin &f" + javaPlugin.getDescription().getName() + " &eis outdated.");
        message.add("&6|");
        message.add("&6| &eCurrent Version &f" + javaPlugin.getDescription().getVersion());
        message.add("&6| &eLatest Version &f" + latestVersionString);
        message.add("&6|");
        message.add("&6| &eDownload latest version here");
        message.add("&6| &f" + getResourceURL());
        message.add("&6|");
        for (String line : message) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }

}