package com.dhlab.xephyr.plugin;

import com.dhlab.xephyr.bukkit.plugin.PluginBootstrapper;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Xephyr bootstrapping class.
 * @author maladr0it
 */
public class Xephyr extends PluginBootstrapper {

    private static Xephyr instance;

    public Xephyr(JavaPlugin plugin) {
        super(plugin);
        instance = this;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public static Xephyr getInstance() {
        return instance;
    }
}
