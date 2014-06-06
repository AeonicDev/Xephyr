package com.dhlab.xephyr.bukkit.kit.plugin.load.internal;

import com.dhlab.xephyr.bukkit.kit.plugin.KitPlugin;
import com.dhlab.xephyr.generic.plugin.loading.IPluginLoadContext;
import org.apache.commons.lang.Validate;

/**
 * A context that directly provides a KitPlugin to add.
 * Literally the simplest thing on the planet.
 * @author maladr0it
 */
public class InternalKitPluginContext implements IPluginLoadContext {

    protected final KitPlugin plugin;

    public InternalKitPluginContext(KitPlugin plugin) {
        Validate.notNull(plugin);
        this.plugin = plugin;
    }

    /**
     * Self-explanatory
     * @return the plugin instance
     */
    public KitPlugin getPlugin() {
        return plugin;
    }
}
