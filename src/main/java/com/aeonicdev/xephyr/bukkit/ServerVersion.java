package com.aeonicdev.xephyr.bukkit;

import org.bukkit.Bukkit;

/**
 * A small class used to get the vanilla version.
 *
 * @author maladr0it
 */
public final class ServerVersion {

    /**
     * Returns the server version.
     *
     * @return The server version string.
     */
    public static String getVersion() {
        String name = Bukkit.getServer().getClass().getPackage().getName();
        return name.substring(name.lastIndexOf('.') + 1);
    }

}
