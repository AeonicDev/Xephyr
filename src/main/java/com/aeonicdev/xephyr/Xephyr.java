package com.aeonicdev.xephyr;

import com.aeonicdev.xephyr.bukkit.helper.inventory.InventoryHelper;
import com.aeonicdev.xephyr.bukkit.plugin.PluginBootstrapper;
import com.aeonicdev.xephyr.generic.helper.HelperRegistry;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Xephyr bootstrapping class.
 *
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
        HelperRegistry.INSTANCE.add(new InventoryHelper());
    }

    @Override
    public void onDisable() {

    }

    public static Xephyr getInstance() {
        return instance;
    }

}
