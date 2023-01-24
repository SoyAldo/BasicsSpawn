package com.soyaldo.basicsspawn.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtil {

    public static Location parse(String format) throws NumberFormatException {
        if (format.equals("undefined")) return null;

        String[] values = format.split(",");
        Location location = null;

        World world = Bukkit.getWorld(values[0]);

        if (world == null) return null;

        double x = Double.parseDouble(values[1]);
        double y = Double.parseDouble(values[2]);
        double z = Double.parseDouble(values[3]);

        if (values.length == 4) {
            location = new Location(world, x, y, z);
        } else if (values.length == 6) {
            float yaw = Float.parseFloat(values[4]);
            float pitch = Float.parseFloat(values[5]);
            location = new Location(world, x, y, z, yaw, pitch);
        }

        return location;
    }

    public static String parse(Location location) {
        if (location == null) return "undefined";

        World world = location.getWorld();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        return world.getName() + "," + x + "," + y + "," + z;
    }

    public static String parse(Location location, boolean yawAndPitch) {
        String format = parse(location);

        if (format.equals("undefined")) return format;

        float yaw = location.getYaw();
        float pitch = location.getPitch();

        return format + "," + yaw + "," + pitch;
    }

}