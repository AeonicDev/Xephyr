package com.dhlab.xephyr.bukkit;

import org.bukkit.Bukkit;

/**
 * @author maladr0it
 */
public final class ServerVersion {

    public static String getVersion() {
        String name = Bukkit.getServer().getClass().getPackage().getName();
        return name.substring(name.lastIndexOf('.') + 1);
    }
}
