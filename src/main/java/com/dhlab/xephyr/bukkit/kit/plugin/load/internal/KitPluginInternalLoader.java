package com.dhlab.xephyr.bukkit.kit.plugin.load.internal;

import com.dhlab.xephyr.bukkit.kit.plugin.KitPlugin;
import com.dhlab.xephyr.generic.plugin.IPluginable;
import com.dhlab.xephyr.generic.plugin.loading.IPluginLoader;

/**
 * Loads internal (or from other Bukkit plugins) KitPlugins.
 * @author maladr0it
 */
public class KitPluginInternalLoader implements IPluginLoader<KitPlugin, InternalKitPluginContext> {
    @Override
    public Class<KitPlugin> getPluginType() {
        return KitPlugin.class;
    }

    @Override
    public Class<InternalKitPluginContext> getContextType() {
        return InternalKitPluginContext.class;
    }

    @Override
    public void loadPlugins(IPluginable<KitPlugin> pluginable, InternalKitPluginContext context) throws Exception {
        // lol
        pluginable.addPlugin(context.getPlugin());
    }
}
