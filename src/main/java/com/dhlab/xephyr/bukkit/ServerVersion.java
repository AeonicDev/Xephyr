package com.dhlab.xephyr.bukkit;

import org.bukkit.Bukkit;

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
